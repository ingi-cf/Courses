import re
import os
import sys
import glob
from ngrams import *
from math import log




class NgramEstimator:

    def __init__(self, folder, n, smoothing = 'laplace'):
        self.n = n
        self.getLexicon(folder)
        self.mngr = [[]]
        for i in range(1,n+1):
            self.mngr.append(ngram(folder, i, '*M_train.txt'))
            if smoothing == 'kneser':
                self.mngr[-1].countNhists()
        self.fngr = [[]]
        for i in range(1,n+1):
            self.fngr.append(ngram(folder, i, '*F_train.txt'))
            if smoothing == 'kneser':
                self.fngr[-1].countNhists()
        if smoothing == 'linear':
            self.computeLambdas()
        
            
    def history(self, sent, k):
        h = sent[k-self.n+1:k]
        for j in range(0, len(h)):
            if h[j] == '</s>':                
                h = h[j+1:]
                return " ".join(h)
        return " ".join(h)
                
    def getLexicon(self, folder):
        self.lexicon = []
        for filename in glob.glob( os.path.join(folder, "lexicon.txt")):
            file = open(filename, "r")
            for line in file:
                for tok in line.rstrip('\n ').rsplit(" "):
                    self.lexicon.append(tok)
            file.close()
    #maximum likelyhood
    def P(self, i, w, h, gender):
            if gender == 'male':
                ngr = self.mngr
            else:
                ngr = self.fngr
            l=0
            if len(h)>0:
                h = h.rstrip('\n ').rsplit(" ")
                l = len(h)
                h = h[i+l-self.n+1:]
                l = len(h)
            if l>0 and l+1<=self.n:
                D = ngr[l].getCount(" ".join(h))
                N = ngr[l+1].getCount(" ".join(h)+" "+w)
            else:
                D = len(ngr[1].tokens)
                N = ngr[1].getCount(w)
                
            if D>0:
                return (N+1)/D #+1 de triche
            else:
                return 0
            
    def computeLambdas(self):
        self.mlambdas = []
        self.flambdas = []
        k = self.n
        msent = self.mngr[1].tokens[0:200]
        fsent = self.fngr[1].tokens[0:200]
        
        #init
        for i in range(0,k):
            self.mlambdas.append(1/k)
            self.flambdas.append(1/k)
            
        
            
        def E(x, sent, gender):
            esum = 0
            
            for j in range(0,len(sent)):
                sum = 0
                w = sent[j]
                h = self.history(sent, j)
                for i in range(0,len(self.mlambdas)):
                    sum = sum+self.mlambdas[i]*self.P(i, w, h, gender)
                if sum>0:
                    esum = esum + self.mlambdas[x]*self.P(x, w, h, gender)/sum
            return esum
                
        def M(x, sent,  gender):
            return E(x, sent, gender)/len(sent)
            
        
        odif = 0
        mdif = 1
        while abs(mdif-odif) > 1/k: 
            odif = mdif
            mdif = 0
            for i in range(0, k):
                nwm = M(i, msent, 'male')
                difm = abs(self.mlambdas[i] - nwm)
                nwf = M(i, fsent, 'female') 
                diff = abs(self.flambdas[i] - nwf)
                self.mlambdas[i] = nwm
                self.flambdas[i] = nwf
                if difm>mdif:
                    mdif = difm
                if diff>mdif:
                    mdif = diff
        
    def plinear(self, w, h, gender):
        if gender == 'male':
            lambdas = self.mlambdas
        else:
            lambdas = self.flambdas
        sum = 0
        for i in range(0, len(lambdas)):
            p = self.P(i, w, h, gender)
            sum = sum + lambdas[i] * p
            #print("lambda : "+str(lambdas[i])+" and sum : "+str(sum)+" and P :"+str(p))
        #print(sum)
        return sum
        
    def Pback(self, w, h, gender):
        #simplest case
        """
        l=0
        if(len(h)>0):
            h = h.rstrip('\n ').rsplit(" ")
            l = len(h)
            h = h[1+l-self.n+1:]
            return self.pkneser(w, " ".join(h), gender)
        else:
            return self.pkneser(w, h, gender)
        """
        if gender == 'male':
            ngr = self.mngr
        else:
            ngr = self.fngr
        l=0
        if(len(h)>0):
            h = h.rstrip('\n ').rsplit(" ")
            l = len(h)
            h = h[1+l-self.n+1:]
            l = len(h)
        #number of different l length histories for w in train
        def count(w):
            if l>0:
                ret = ngr[l+1].countDiffH(w) +1 #+1 de triche
                if ret == 0:
                    print("error ret is 0 for "+w)
                return ret
            else:
                return ngr[1].getCount(w) +1 #+1 de triche
        C = count(w)
        sum = 0
        for word in ngr[1].lexicon.keys():
            sum = sum + count(word)
        return C/sum
    def pkneser(self, w, h, gender):
        if gender == 'male':
            ngr = self.mngr
        else:
            ngr = self.fngr
        
        
        def gamma(h, dc, ch):
            sum = 0
            l=0
            if(len(h)>0):
                l = len(h.rstrip('\n ').rsplit(" "))
                for word in self.lexicon:
                    if ngr[l+1].getCount(h+" "+word) > 0:
                        sum = sum + dc/ch
            else : 
                for word in range(0, len(ngr[1].lexicon)):
                    sum = sum + dc/ch
            return sum
            
        n1 = ngr[self.n].n1
        n2 = ngr[self.n].n2
        dc = n1/(n1+2*n2)
        l=0
        if(len(h)>0):
            l = len(h.rstrip('\n ').rsplit(" "))
            chw = ngr[l+1].getCount(h+" "+w)
            ch = ngr[l].getCount(h)
        else:
            chw = ngr[1].getCount(w)
            ch = len(ngr[1].tokens)
            
        pback = self.Pback(w,h,gender)
        gamm = gamma(h, dc, ch)
        
        
        #print("chw : "+str(chw)+" ch : "+str(ch)+" gamm : "+str(gamm)+" pback : "+str(pback))
        if chw > 0 :
            return (chw-dc)/ch + gamm*pback
        elif ch > 0:
            return gamm*pback
        else:
            return pback
        
    #compute the laplace-smoothed probability
    def plaplace(self, w, h, gender):
        if gender == 'male':
            ngr = self.mngr
        else:
            ngr = self.fngr
        l = 0
        if len(h)>0 and self.n>1:
            l = len(h.rstrip('\n ').rsplit(" "))
        w = w.rstrip('\n ')
        h = h.rstrip('\n ')
        N=0
        D=0
        if l<=0:
            N = (ngr[1].getCount(w)+1)
            D = len(self.lexicon)
        else:
                N =  (ngr[l+1].getCount(h+" "+w)+1)
                D = (ngr[l].getCount(h)+len(self.lexicon))
        if D == 0:
            return 0
        else :
            #print(h+" "+w+" l ="+str(l)+": "+str(N/D))
            return N/D
    
    def consistency(self, gender, max=50, smoothing = 'laplace'):
        if gender == 'male':
            ngr = self.mngr[self.n]
        else :
            ngr = self.fngr[self.n]
        for hist in ngr.getHistories(max):
            sum = 0
            for w in self.lexicon:
                if smoothing == 'laplace':
                    p = self.plaplace(w,hist, gender)
                elif smoothing == 'linear':
                    p = self.plinear(w,hist, gender)
                elif smoothing == 'kneser':
                    p = self.pkneser(w,hist, gender)
                else:
                    print("error : unknown smoothing")
                sum = sum + p
            if abs(1-sum)>pow(10,-9):
                print("consistency error on "+gender+" model : sum="+str(sum))
                return False
        return True
    
    def perplexity(self, sent, gender, smoothing = 'laplace'):
        sum = 0
        sent = sent.rstrip('\n ').rsplit(" ")
        M = len(sent)
        for i in range(0,M):
            h = self.history(sent, i)
            if smoothing == 'laplace':
                p = self.plaplace(sent[i], h, gender)
            elif smoothing == 'linear':
                p = self.plinear(sent[i], h, gender)
            elif smoothing == 'kneser':
                p = self.pkneser(sent[i], h, gender)
            else:
                print("error : unknown smoothing")
                
            sum = sum + log(p,2)
        sum = -sum/M
        return pow(2,sum)
    
    def predict(self, text, smoothing = 'laplace'):
        
        genders = ['male', 'female']
        #compute probabilities product for each gender
        probs = [0, 0]
        text = text.rstrip('\n ').rsplit(" ")
        for k in range(0, len(text)): #each word
            w=text[k]
            
            if not w=='<s>': #don't predict start of sentence
                h = self.history(text, k)
                
                if smoothing == 'laplace':
                    pm = self.plaplace(w,h ,genders[0])
                    pf = self.plaplace(w,h ,genders[1])
                elif smoothing == 'linear':
                    pm = self.plinear(w,h ,genders[0])
                    pf = self.plinear(w,h ,genders[1])
                elif smoothing == 'kneser':
                    pm = self.pkneser(w,h ,genders[0])
                    pf = self.pkneser(w,h ,genders[1])
                else:
                    print("error : unknown smoothing")
                probs[0] = probs[0] + log(pm)
                probs[1] = probs[1] + log(pf)
                """
                probs[0] = probs[0] + log(self.plinear(w,h ,genders[0]))
                probs[1] = probs[1] + log(self.plinear(w,h ,genders[1]))
                """
                
                
        #chose most probable gender
        #print("probability for male is "+str(probs[0])+ " and for female :"+str(probs[1]))
        if probs[0] > probs[1]:
            return genders[0]
        elif probs[0] < probs[1]:
            return genders[1]
        else:
            return 'undetermined'
                



    