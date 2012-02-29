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
        self.values['gender'] = self.tSVal.get('gender')
        self.values['age'] = self.tSVal.get('age')

        #weight
        if(len(self.tSVal.get('weight_kg'))>0):
            self.values['weight'] = self.tSVal.get('weight_kg')[-1]
        elif(len(self.tSVal.get('weight_pounds'))>0):
            self.values['weight'] = int(self.tSVal.get('weight_pounds')[-1])*0.45359237

        #height
        if(len(self.tSVal.get('height_cm'))>0):
            self.values['height'] = float(self.tSVal.get('height_cm')[-1]) / 100
        elif(len(self.tSVal.get('height_m'))>0):
            self.values['height'] = self.tSVal.get('height_m')[-1]
        elif(len(self.tSVal.get('height_feet'))>0):
            feet,inches = re.match("(.*)\'(.*)\"",self.tSVal.get('height_feet')[-1]).groups()
            self.values['height'] = float(feet)*0.3048 + float(feet)*0.0254

        #bmi
        if(len(self.tSVal.get('bmi'))>0):
            self.values['bmi'] = self.tSVal.get('bmi')[-1]
        elif(self.values['weight'] and self.values['height']):
            self.values['bmi'] = float(self.values.get('weight')) / (float(self.values.get('height')))**2
            


    
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
                ret += str(v) 
            else:
                ret += "None"
            ret += ",\t"

        return ret[:-2]
