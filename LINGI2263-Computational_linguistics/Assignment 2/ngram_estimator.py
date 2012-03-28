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
    def plaplace(self, w, h, gender = 'male'):
        if gender == 'male':
            ngr = self.mngr
        else:
            ngr = self.fngr
        l = len(h.rstrip('\n ').rsplit(" "))
        w = w.rstrip('\n ')
        h = h.rstrip('\n ')
        N=0
        D=0
        if l >= self.n-1 :
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
            
    def predictLaplace(self, text):
        text = text.rstrip('\n ').rsplit(" ")
        genders = ['male', 'female']
        probs = [1, 1]
        
        for i in range(0,2):
            for j in range(0,len(text)):
                probs[i] = probs[i] * plaplace(text[j]," ".join(text[j-self.n+1, j]) ,genders[i])
                
        if probs[0] > probs[1]:
            return genders[0]
        else:
            return genders[1]
                
testE = NgramEstimator("tokenized",3)

print(testE.plaplace("in","it is", 'male'))
    