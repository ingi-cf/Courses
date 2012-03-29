import re
import os
import glob
import sys
from ngram_estimator import *

#get models, can take some time
testE = NgramEstimator("tokenized",5)

#testE.computeLambdas()
"""
print(testE.flambdas)
print(testE.mlambdas)

if testE.consistencyLap('male',5):
    print("male model is consistent with laplace smoothing")
if testE.consistencyLap('female',5):
    print("female model is consistent with laplace smoothing")
"""

#read test file and classifies lines
filename = "tokenized/Blogs_F_test.txt"
file = open(filename, "r")
for i in range(0,15):
    line = file.readline().rstrip('\n ')
    
    print("perplexM "+str(testE.perplexity(line, 'male'))+"perplexF "+str(testE.perplexity(line, 'female'))+" from "+filename+" as "+testE.predictLap(line))

file.close()