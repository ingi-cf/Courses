import re
import os
import sys
import glob
from ngrams import *


class NgramEstimator:

    def __init__(self, folder, n):
        self.n = n
        self.getLexicon(folder)
        self.ngr = [[]]
        for i in range(1,n+1):
            self.ngr.append(ngram(folder, i, '*train.txt'))
        
                
    def getLexicon(self, folder):
        self.lexicon = []
        for filename in glob.glob( os.path.join(folder, "lexicon.txt")):
            file = open(filename, "r")
            for line in file:
                for tok in line.rstrip('\n ').rsplit(" "):
                    self.lexicon.append(tok)
            file.close()
            
    #compute the laplace-smoothed probability
    def plaplace(self, w, h):
        l = len(h.rstrip('\n ').rsplit(" "))
        w = w.rstrip('\n ')
        h = h.rstrip('\n ')
        N=0
        D=0
        if l >= self.n-1 :
            N = (self.ngr[self.n].getCount(h+" "+w)+1)
            D = (self.ngr[self.n-1].getCount(h)+len(self.lexicon))
        for i in range(2,self.n):
            if l == self.n-i:
                N =  (self.ngr[self.n-i+1].getCount(h+" "+w)+1)
                D = (self.ngr[self.n-i].getCount(h)+len(self.lexicon))
        if D == 0:
            return 0
        else :
            return N/D
                
testE = NgramEstimator("tokenized",3)

print(testE.plaplace("in","it is"))
    