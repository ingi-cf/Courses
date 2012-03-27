import re

def tokenize(string):
    patterns = []
    patterns.append("[\w\-]+") #any word
    patterns.append("\'s") #'s
    patterns.append("[:()@pPDdxXsS-_^]{2,3}") #smileys
    patterns.append("[0-9=\-*\/+%()]+") #mathematical exp or date
    patterns.append("[a-z._]+@[a-z.]+.[a-z]{2,3}") #mail adress
    return re.findall("|".join(patterns),string)


file = open("blogs/Blogs_F_train.txt", "r")
lexicon={}

#tokenize each line
for line in file:
    tokens = tokenize(line)
    for t in tokens:
        if(t in lexicon):
            lexicon[t] = lexicon[t] +1
        else:
            lexicon[t] = 1

#obtain 20 most used tokens
lexicon_sorted = sorted(lexicon.items(), key=lambda pair:pair[1], reverse=True)
print(lexicon_sorted[0:20])
