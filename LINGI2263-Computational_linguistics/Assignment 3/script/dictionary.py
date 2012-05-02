import math
class Dictionary:
    
    defs        = None;
    def_with_w  = None;     #number of definition in wich each word appears
    idf         = None;

    def __init__(self):
        self.defs = {}
        self.def_with_w = {}


    def insert(self,adef):
        #add all words of the definition in the count of number of definition per word
        for w in adef.defWords():
            if w not in self.def_with_w:
                self.def_with_w[w] = 0
            self.def_with_w[w] += 1
        self.defs[adef.wDefined()] = adef

    def computeIDF(self):
        self.idf = {}
        for w in self.def_with_w.keys():
            self.idf[w] = math.log(len(self.defs) / self.def_with_w[w])

    def getIDF(self):
        if self.idf==None:
            self.computeIDF()
        return self.idf

    def getDefs(self):
        return self.defs

    def getAllTFIDFV(self):
        tfidfvs = {}
        for k,v in self.defs.items():
            tfidfvs[k] = v.getTFIDFV()
        return tfidfvs
