import re
import os
import glob
import sys

class ngram:
    lexicon = {}
    
    def countNgrams(self):
        self.lexicon = {}
        for t in range(self.n,len(self.tokens)+1):
            gram = " ".join(self.tokens[t-self.n:t])
            if(gram in self.lexicon):
                self.lexicon[gram] = self.lexicon[gram] +1
            else:
                self.lexicon[gram] = 1
                
    def getHistories(self,  max = 999):
        ret = []
        c = 0
        for i in range(0,len(self.tokens)):
                ret.append(" ".join(self.tokens[i-self.n+1:i]))
                c = c+1
                if c == max:
                    return ret  
        return ret
        
    def getOrderedGrams(self):
        return sorted(self.lexicon.items(), key=lambda pair:pair[1], reverse=True)
        
    def getCount(self, str):
        if self.n == 0:
            return 0
        elif str in self.lexicon:
            return self.lexicon[str]
        else:
            return 0
        
    def __init__(self, folder, n, mask):
        self.n = n 
        self.tokens = []
        #get tokens from file
        for filename in glob.glob( os.path.join(folder, mask)):
            file = open(filename, "r")
            for line in file:
                for tok in line.rstrip('\n ').rsplit(" "):
                    self.tokens.append(tok)
            file.close()
        self.countNgrams()





    
    