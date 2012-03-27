import string
import re

regexTypes = {
            "EMAIL":r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$"
            ,"DATE":r"(([0-9]{1,4})[-\\\/.]?){3}"
            ,"HUGSKISS":r"([xX][.-_]?[oO][.-_]?)+"

            }

def isCapsWeird(token):
    hasAWeirdCap = False
    hasARegular = False

    for i in range(1,len(token)):
        if (token[i].isupper() and token[i-1] != "-"):
            hasAWeirdCap =  True;
        elif(token[i].islower()):
            hasARegular = True
        if(hasARegular and hasAWeirdCap):
            return True
    return False;

def isCharReapeated(token):
    lastChar = ""
    lastCharRep = 0
    for i in range(1,len(token)):
        if (token[i] == lastChar):
            lastCharRep += 1
        else:
            lastChar    = token[i]
            lastCharRep = 0
        if lastCharRep == 3:
            return True
    return False;

types = {    "CAPWEIRD":isCapsWeird
            ,"CHARREAPEATED":isCharReapeated
        }

def getTypesRegex():
    return regexTypes.values()



def findType(token):
    for rk,rt in regexTypes.items():
        if(re.match(rt,token)):
            return rk
    for k,tf in types.items():
        if(tf(token)):
            return k
    return token

tokens = ["HelloOoo", "Hello", "Marie-Claire", "hellooooooooo", "foot", "12/2/2012", "10-10-23","prive@frol.be","X.O.X.O","XoXo"];


for token in tokens:
        print(token + " " + findType(token) )
