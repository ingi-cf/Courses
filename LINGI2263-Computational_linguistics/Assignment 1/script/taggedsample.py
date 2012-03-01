import re


class TaggedSample:
    tags    = None
    values  = None

    def __init__(self, tags=None):
        self.tags    = []
        self.values  = {}
        if tags:
            for tag in tags:
                self.addTag(tag)

    def addTag(self, tag):
        self.tags.append(tag)
        self.values[tag] = []

    def parse(self, sample):
        for tag in self.tags:
            tagStart    = '{' + tag + '}'
            tagEnd      = '{/' + tag + '}'
            exp = tagStart + '[^{]*' + tagEnd
            elems = re.findall(exp, sample)
            for elem in elems:
                self.values[tag].append(elem[len(tagStart):-len(tagEnd)])

    def get(self,key):
        if len(self.values[key]) == 0:
            return None
        else:
            return self.values[key]
    
    def __repr__(self):
        ret = ""
        for tag in self.tags:
            ret += tag + ":\n"
            for val in self.values[tag]:
                ret += "\t" + val + "\n"

        return ret

    def summary(self):
        ret = ""
        for tag in self.tags:
            ret += tag + ":" + str(len(self.values[tag])) + "\t"
        
        return ret

