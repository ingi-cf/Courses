
import re

class SampleReader:

    separator   = "<separator>"
    stream      = None

    def __init__(self):
        pass

    def setSeparator(self, separator):
        self.separator = separator

    def setStream(self,stream):
        self.stream = stream

    def getSample(self):
        buff = ""
        matchingSeparator = ""
        for line in self.stream:
            buff += line
            matchingSeparator += line
            if self.isPartialSeparator(matchingSeparator):
                if re.match(self.separator+"$", matchingSeparator):
                    yield buff[:-len(matchingSeparator)]
                    buff = ""
                    matchingSeparator = ""
            else:
                matchingSeparator = ""
      
        yield buff

    def isPartialSeparator(self, s_partial):
        if(len(s_partial) > len(self.separator)):
            return False

        for i in range(len(s_partial)):
            if self.separator[i] == s_partial[i]:
                return False

        return True
