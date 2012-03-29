import re
import os
import glob
import sys
from ngram_estimator import *



smooth = 'linear'
#get models, can take some time
testE = NgramEstimator("tokenized",3, smoothing = smooth)

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
filename = "tokenized/Blogs_M_test.txt"
file = open(filename, "r")
for i in range(0,10):
    line = file.readline().rstrip('\n ')
    
    print("perplexM "+str(testE.perplexity(line, 'male', smoothing = smooth))+"perplexF "+str(testE.perplexity(line, 'female', smoothing = smooth))+" from "+filename+" as "+testE.predict(line, smoothing = smooth))

file.close()