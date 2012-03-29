import re
import os
import glob
import sys
from ngram_estimator import *

#get models, can take some time
testE = NgramEstimator("tokenized",5)

#get confusion matrix : 
male = [0, 0] #cat as male/female
female = [0,0]

filename = "tokenized/Blogs_F_test.txt"
file = open(filename, "r")
for line in file:
    result = testE.predictLap(line)
    if result == 'male':
        female[0] = female[0] + 1
    elif result == 'female':
        female[1] = female[1] + 1

file.close()

filename = "tokenized/Blogs_M_test.txt"
file = open(filename, "r")
for line in file:
    result = testE.predictLap(line)
    if result == 'male':
        male[0] = male[0] + 1
    elif result == 'female':
        male[1] = male[1] + 1
file.close()

print(str(male[0])+" & "+str(male[1]))
print(str(female[0])+" & "+str(female[1]))

output = open("confusionmatrix.txt", "w")
output.write("with laplace : \n")
output.write(str(male[0])+" & "+str(male[1])+" \\ \n" )
output.write(str(female[0])+" & "+str(female[1])+" \\ \n" )
output.close()
