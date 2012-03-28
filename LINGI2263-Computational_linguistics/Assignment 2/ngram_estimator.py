import re
import os
import sys
import glob
from ngrams import *


class NgramEstimator:

    def __init__(self, folder, n):
        self.n = n
        self.getLexicon(folder)
        self.mngr = [[]]
        for i in range(1,n+1):
            self.mngr.append(ngram(folder, i, '*M_train.txt'))
        self.fngr = [[]]
        for i in range(1,n+1):
            self.fngr.append(ngram(folder, i, '*F_train.txt'))
            
        
                
    def getLexicon(self, folder):
        self.lexicon = []
        for filename in glob.glob( os.path.join(folder, "lexicon.txt")):
            file = open(filename, "r")
            for line in file:
                for tok in line.rstrip('\n ').rsplit(" "):
                    self.lexicon.append(tok)
            file.close()
            
    #compute the laplace-smoothed probability
    def plaplace(self, w, h, gender):
        
            
        if gender == 'male':
            ngr = self.mngr
        else:
            ngr = self.fngr
        l = len(h.rstrip('\n ').rsplit(" "))
        w = w.rstrip('\n ')
        h = h.rstrip('\n ')
        N=0
        D=0
        if len(h)<=0:
            N = (ngr[self.n].getCount(h+" "+w)+1)
            D = len(self.lexicon)
        elif l >= self.n-1 :
            N = (ngr[self.n].getCount(h+" "+w)+1)
            D = (ngr[self.n-1].getCount(h)+len(self.lexicon))
        for i in range(2,self.n):
            if l == self.n-i:
                N =  (ngr[self.n-i+1].getCount(h+" "+w)+1)
                D = (ngr[self.n-i].getCount(h)+len(self.lexicon))
        if D == 0:
            return 0
        else :
            return N/D
            
    def consistency(self, gender, max=999):
        if gender == 'male':
            ngr = self.mngr[self.n]
        else :
            ngr = self.fngr[self.n]
        for hist in ngr.getHistories(max):
            sum = 0
            for w in self.lexicon:
                sum = sum + self.plaplace(w,hist, gender)
            if abs(1-sum)>pow(10,-9):
                print("consistency error on "+gender+" model : sum="+str(sum))
                return False
        return True
            
    def predict(self, text):
        #split text by sentences
        text = text.rstrip('\n ').rsplit("<s>")
        for senti in range(0,len(text)):
            text[senti] = text[senti].rstrip('\n ').rsplit(" ")
        
        genders = ['male', 'female']
        #compute probabilities product for each gender
        probs = [1, 1]
        for j in range(0,len(text)): #each sentence
            for k in range(0, len(text[j])): #each word
                w=text[j][k]
                h = " ".join(text[j][k-self.n+1: k])
                probs[0] = probs[0] * self.plaplace(w,h ,genders[0])
                probs[1] = probs[1] * self.plaplace(w,h ,genders[1])
                #stupid workaround because probabilities get too low to be computed
                if probs[0]<0.000001 and probs[1]<0.000001:
                    probs[0] = probs[0]*100000
                    probs[1] = probs[1]*100000
                
        #chose most probable gender
        print("probability for male is "+str(probs[0])+ " and for female :"+str(probs[1]))
        if probs[0] > probs[1]:
            return genders[0]
        elif probs[0] < probs[1]:
            return genders[1]
        else:
            return 'undetermined'
                
#get models, can take some time
testE = NgramEstimator("tokenized",5)



if testE.consistency('male',5):
    print("male model is consistent")
if testE.consistency('female',5):
    print("female model is consistent")

#read test file and classifies lines
filename = "tokenized/Blogs_M_test.txt"
file = open(filename, "r")
for i in range(0,10):
    line = file.readline().rstrip('\n ')
    print("predicted line "+str(i)+" from "+filename+" as "+testE.predict(line))

file.close()


    