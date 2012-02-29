
import re

class SampleReader:

    separator   = "hi"
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
            match = re.match(matchingSeparator,self.separator)
            if match:
                if re.match(self.separator+"$", matchingSeparator):
                    yield buff[:-len(matchingSeparator)]
                    buff = ""
                    matchingSeparator = ""
            else:
                matchingSeparator = ""
      
        yield buff
