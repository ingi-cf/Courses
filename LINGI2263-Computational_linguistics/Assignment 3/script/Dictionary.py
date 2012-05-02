import Dictionary

class Dictionary:
    
    definitions = None;
    def_with_w  = None;     #number of definition in wich each word appears

    def __init__(self):
        self.definitions = {}
        self.def_with_w = {}


    def insert(self,definition):
        #add all words of the definition in the count of number of definition per word
        for w in definition.defWords():
            if w not in def_with_w:
                def_with_w[w] = 0
            def_with_w[w] += 1

        definitions{definition.wDefined()} = definition
        
