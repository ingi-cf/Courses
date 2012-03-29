import re
import os
import glob
import sys
from ngram_estimator import *

#get models, can take some time

output = open("confusionmatrix.txt", "w")
for order in [1,3,5]:
    for smooth in ['laplace', 'linear']:
        testE = NgramEstimator("tokenized",order, smoothing = smooth)
        #get confusion matrix : 
        male = [0, 0] #cat as male/female
        female = [0,0]

        filename = "tokenized/Blogs_F_test.txt"
        file = open(filename, "r")
        for line in file:
            result = testE.predict(line, smoothing = smooth)
            if result == 'male':
                female[0] = female[0] + 1
            elif result == 'female':
                female[1] = female[1] + 1

        file.close()

        filename = "tokenized/Blogs_M_test.txt"
        file = open(filename, "r")
        for line in file:
            result = testE.predict(line, smoothing = smooth)
            if result == 'male':
                male[0] = male[0] + 1
            elif result == 'female':
                male[1] = male[1] + 1
        file.close()
        
        print(str(order)+"-grams with "+smooth+" smoothing : ")
        print(str(male[0])+" & "+str(male[1]))
        print(str(female[0])+" & "+str(female[1]))



        output.write(str(order)+"-grams with "+smooth+" smoothing : \n")
        output.write(str(male[0])+" & "+str(male[1])+" \\ \n" )
        output.write(str(female[0])+" & "+str(female[1])+" \\ \n" )
output.close()
