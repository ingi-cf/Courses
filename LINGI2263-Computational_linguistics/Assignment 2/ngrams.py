import re
import os
import glob
import sys

class ngram:
    lexicon = {}
    def countNgrams(self, tokens, n):
        self.n = n
        self.lexicon = {}
        for t in range(n,len(tokens)+1):
            gram = " ".join(tokens[t-n:t])
            if(gram in self.lexicon):
                self.lexicon[gram] = self.lexicon[gram] +1
            else:
                self.lexicon[gram] = 1
        
    def getOrderedGrams(self):
        return sorted(self.lexicon.items(), key=lambda pair:pair[1], reverse=True)
        
    def getCount(self, str):
        if self.n == 0:
            return 0
        elif str in self.lexicon:
            return self.lexicon[str]
        else:
            return 0
        
    def __init__(self, folder, n, mask = '*train.txt'):
        tokens = []
        
        #get tokens from file
        for filename in glob.glob( os.path.join(folder, mask)):
            file = open(filename, "r")
            for line in file:
                for tok in line.rstrip('\n ').rsplit(" "):
                    tokens.append(tok)
            file.close()
        self.countNgrams(tokens, n)





    
    