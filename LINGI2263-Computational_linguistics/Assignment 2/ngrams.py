import re
import os
import glob
import sys

class ngram:
    lexicon = {}
    
    def countDiffH(self, w):
        if w in self.nhist:
            return self.nhist[w]
        else:
            return 0
        
    
    def countNgrams(self):
        self.lexicon = {}
        
        for t in range(self.n,len(self.tokens)+1):
            gram = " ".join(self.tokens[t-self.n:t])
            if(gram in self.lexicon):
                self.lexicon[gram] = self.lexicon[gram] +1
                
            else:
                self.lexicon[gram] = 1
            #generated unique histories count for each word
            """
            if t>0 :
                if self.tokens[t-1] in self.nhist:
                    if " ".join(self.tokens[t-self.n:t-1]) not in hists[self.tokens[t-1]]:
                        hists[self.tokens[t-1]].append(" ".join(self.tokens[t-self.n:t-1]))
                        self.nhist[self.tokens[t-1]] = self.nhist[self.tokens[t-1]] + 1
                else:
                    hists[self.tokens[t-1]] = [" ".join(self.tokens[t-self.n:t-1])]
                    self.nhist[self.tokens[t-1]] = 1
            """
            
        print(str(self.n)+"-grams generated")
        
    def countNhists(self):
        self.nhist = {} #number of unique histories
        hists = {}
        for key in self.lexicon.keys():
            word = key.rstrip("\ ").rsplit(" ")[-1]
            hi = " ".join(key.rstrip("\ ").rsplit(" ")[0:-1])
            if word in self.nhist:
                if hi not in hists[word]:
                    hists[word][hi] = 1
                    self.nhist[word] = self.nhist[word] + 1
            else:
                hists[word] = {}
                hists[word][hi] = 1
                self.nhist[word] = 1
                
    def getHistories(self,  max = 999):
        ret = []
        c = 0
        for i in range(0,len(self.tokens)):
                ret.append(" ".join(self.tokens[i-self.n+1:i]))
                for j in range(0, len(ret[-1])):
                    if ret[-1][j] == '</s>':                
                        ret[-1] = ret[-1][j+1:]
                        j = 99
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
        
        #get n1 and n2
        self.n1 = 0
        self.n2 = 0
        for val in self.lexicon.values():
            if val == 1:
                self.n1 = self.n1+1
            elif val==2:
                self.n2 = self.n2+1





    
    