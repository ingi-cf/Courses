import re
import os
import glob
import sys
from ngrams import *

#get source files folder
input = "tokenized"
if(len(sys.argv)>1):
    input = sys.argv[1]
    


#make counts for log log graph
counts = {}
for i in range(0,3):
    ngr = ngram(input, i+1)
    lexicon_sorted = ngr.getOrderedGrams()
    print("top 20 "+str(i+1)+"-grams : ")
    
    for lex in lexicon_sorted[0:20]:
        print(str(lex[0]) + "  :  " +str(lex[1]))
    for lex in lexicon_sorted:
        if lex[1] in counts:
            counts[lex[1]][i] = counts[lex[1]][i]+1
        else:
            counts[lex[1]] = [0,0,0]
            counts[lex[1]][i] = 1
    


#write number of grams(count) in file
file = open("ngrams_data.txt", "w")
for key in sorted(counts.keys()):
    file.write(str(key)+"\t"+str(counts[key][0])+"\t"+str(counts[key][1])+"\t"+str(counts[key][2])+"\n")

file.close()