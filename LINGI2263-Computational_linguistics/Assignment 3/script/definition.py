for ttree import lemmeAndFilter

class Definition:
    
    word = None
    card_w_def = None

    def __init__(self, word, definition):
        self.word = word
        self.card_w_def = {}
        pass

    def wDefined(self):
        return self.wDefined
    
    def defWords(self):
        return self.keys()

    def compute(self,textDef):
        wDed = lemmeAndFilter(textDef)
        for w in wDef:
            if w not in card_w_def:
                card_w_def[w] = 0
            card_w_def[w] += 1
