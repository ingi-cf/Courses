import re
import os
import glob
import sys

#get source files folder
input = "tokenized"
if(len(sys.argv)>1):
    input = sys.argv[1]
    
tokens = []

#get tokens from file
for filename in glob.glob( os.path.join(input, '*.txt')):
    file = open(filename, "r")
    for line in file:
        for tok in line.rsplit(" "):
            tokens.append(tok)
    file.close()

def countNgrams(tokens, n):
    lexicon = {}
    for t in range(n-1,len(tokens)):
        gram = tokens[t]
        for i in range(1,n):
            gram = tokens[t-i]+ " " +gram
        if(gram in lexicon):
            lexicon[gram] = lexicon[gram] +1
        else:
            lexicon[gram] = 1
    return sorted(lexicon.items(), key=lambda pair:pair[1], reverse=True)
    
for i in range(1,4):
    lexicon_sorted = countNgrams(tokens,i)
    print("top 20 "+str(i)+"-grams : ")
    for lex in lexicon_sorted[0:20]:
        print(str(lex[0]) + "  :  " +str(lex[1]))