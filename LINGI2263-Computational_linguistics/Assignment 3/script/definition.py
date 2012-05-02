from ttree import lemmeAndFilter
import math

class Definition:
    
    word        = None
    card_w_def  = None
    def_len     = 0
    tfidf_v     = None

    def __init__(self, word, definition):
        self.word = word
        self.card_w_def = {}
        self.compute(definition)
        pass

    def wDefined(self):
        return self.word
    
    def defWords(self):
        return self.card_w_def.keys()

    def compute(self,textDef):
        wDef = lemmeAndFilter(textDef)
        for w in wDef:
            if w not in self.card_w_def:
                self.card_w_def[w] = 0
            self.card_w_def[w] += 1
            self.def_len +=1

    def computeTFIDFV(self,idf):
        self.tfidf_v = {}
        for w in self.card_w_def:
            self.tfidf_v[w] = (self.card_w_def[w] / self.def_len) * idf[w]
        self.normalizeTFIDFV()
        return self.getTFIDFV

    def normalizeTFIDFV(self):
        s = 0
        for w in self.card_w_def:
            s += self.tfidf_v[w] ** 2
        
        s = math.sqrt(s)
        for w in self.card_w_def:
            self.tfidf_v[w] = self.tfidf_v[w] / s

    def getTFIDFV(self):
        return self.tfidf_v


