import string
import re

regexTypes = {
            "<email>":r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$"
            ,"<date>":r"(([0-9]{1,4})[-\\\/.]?){3}"
            ,"<kikoo>":r"([xX][.-_]?[oO][.-_]?)+"
            ," is":"\'s"
            ," am":"\'m"
            ,"<smiley>":"(?<!\w)[:()@pPDdxXsS-_^\[\]]{2,3}(?!\w)"
            ,"<math>":"[0-9=\-*\/+%()^]{3,}"
            ,"<punctuationfreak>":r"[?!]{2,}"
            ,"<repeatedchars>":r"\w{0,}([a-zA-Z])\1{2,}\w{0,}"
            ,"<weirdcaps>":r"[\w\-]{0,}[a-z][A-Z][\w\-]{0,}"
            #,"<abbreviation>":r"(?<!\w)\w(?<![aAIO])(?!\w)"
            }
"""
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

types = {    "<weirdwaps>":isCapsWeird
            ,"":isCharReapeated
        }
"""

def getTypesRegex():
    return regexTypes.values()



def findType(token):
    for rk,rt in regexTypes.items():
        if(re.match(rt,token)):
            return rk
            """
    for k,tf in types.items():
        if(tf(token)):
            return k
            """
    return token
"""
tokens = ["HelloOoo", "Hello", "Marie-Claire", "hellooooooooo", "foot", "12/2/2012", "10-10-23","prive@frol.be","X.O.X.O","XoXo","?!?","test","??"];


for token in tokens:
        print(token + " " + findType(token) )

"""