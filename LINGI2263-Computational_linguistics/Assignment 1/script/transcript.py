class Transcript:
    s_name = None
    s_type = None

    tSVal  = None

    units = {'gender':'', 'age':'', 'weight':'kg', 'height':'m', 'bmi':''
            ,'temperature':'°C', 'pulse':'BPM', 'breathing frequency':'breadth per minute','blood pressure':'mmHg','oxygen saturation':'%'}
    values = {}

    def __init__(self,taggedSample):
        self.tSVal = taggedSample
        s_name = self.tSVal.get('sample_name')
        s_type = self.tSVal.get('sample_type')
        self.findBasic()

    def findBasic(self):
        gender = self.tSVal.get('gender')
    
    def findVital(self):
        pass

    def __str__(self):
        ret = ""
        #ret += 'sample name:' + self.s_name
        #ret += 'sample type:' + self.s_type
        for (k,v) in self.units.items():
            ret += k + ":"
            if k in self.values:
                ret += self.values[k]
                ret += v 
            else:
                ret += "None"
            ret += ",\t"

        return ret[:-2]
