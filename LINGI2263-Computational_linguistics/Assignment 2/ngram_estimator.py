import re
import os
import sys
import glob
from ngrams import *


class NgramEstimator:
    n = 3
    ngr
    lexicon
    
    def __init__(self, folder, n):
        self.n = n
        self.getLexicon(folder)
        self.getGramsCounts(folder)
        
        
    def getGramsCounts(self, folder, mask = '*train.txt'):
        self.ngr = ngram(folder, self.n, mask)
        
    def getLexicon(self, folder):
        self.lexicon = []
        for filename in glob.glob( os.path.join(folder, "lexicon.txt")):
            file = open(filename, "r")
            for line in file:
                for tok in line.rstrip('\n ').rsplit(" "):
                    self.lexicon.append(tok)
            file.close()
            
    def plaplace(self, w, h):
        return (ngr.getCount(h+w)+1)/(ngr.getCount(h)+len(lexicon))
    