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
        for tok in re.findall("</?\w+>|[\w\-'\\\']+",line):
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
    



counts = {}
for i in range(0,3):
    lexicon_sorted = countNgrams(tokens,i+1)
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



    
    