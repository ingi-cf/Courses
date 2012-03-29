import re
import os
import glob
import sys
from ngram_estimator import *



smooth = 'linear'
output = open("q4results_femaletest.txt", "w")

for order in [1,2,3,4,5]:
    for smooth in ['laplace', 'linear']:
        #get models, can take some time
        testE = NgramEstimator("tokenized",order, smoothing = smooth)
        #testE.consistency('male', max=10)
        #testE.consistency('female', max=10)
        #read test file and classifies lines

        
        filename = "tokenized/Blogs_F_test.txt"
        file = open(filename, "r")
        sum = 0
        pm = 0
        oovm = 0
        pf = 0
        oovf = 0
        for line in file:       
            sum = sum +1
            (o, p) = testE.perplexity(line, 'male')
            pm = pm + p
            oovm = oovm + o
            (o, p) = testE.perplexity(line, 'female')
            pf = pf + p
            oovf = oovf + o
            #print("perplexM "+str(pm/sum)+"perplexF "+str(pf/sum)+" from "+filename+" as "+testE.predict(line))
        output.write("\subsection{"+str(order)+"-gram with "+smooth+" smoothing :} \n")
        #output.write("mean perplexity on female test : "+str(pf/sum)+" and oov : "+str(oovf/sum)+"\n")
        output.write("\\begin{tabular}{|r|c|c|} \n")
        output.write("\hline \n")
        output.write(" & mean perplexity & mean perplexity OOV rate \\\\ \n")
        output.write("\hline \n")
        output.write("female & "+str(pf/sum)+" & "+str(oovf/sum)+" \\\\ \n")
        output.write("male & "+str(pm/sum)+" & "+str(oovm/sum)+" \\\\ \n")
        output.write("\hline \n")
        output.write("\\end{tabular} \n")
        #output.write("mean perplexity on male test : "+str(pm/sum)+" and oov : "+str(oovm/sum)+"\n")
        print("finished " +str(order)+"-gram with "+smooth+" smoothing")
        file.close()
        
output.close()