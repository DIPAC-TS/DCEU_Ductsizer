from enum import Enum, auto

class Fitting(object):
    class Type(Enum):
        CROSS = auto()
        DAMPER = auto()
        ELBOW = auto()
        EXIT = auto()
        TEE = auto()
        TRANSITION = auto()
        UNCLASSIFIED = auto()
        WYE = auto()
        
        @classmethod
        def has_key(cls, name):
            return name in cls.__members__
            
    def __init__(self, schema:dict):
        if (not self.__validate(schema)):
            raise Exception("The data in the schema is not compatible, check the size of elements")
        self.set_name(schema["name"])
        self.fittype = schema["type"]
        self.system = schema["system"]
        self.description = schema["description"]
        if ("rel_k" in schema.keys()):
            self.rel_k = schema["rel_k"]
        else:
            self.rel_k = None        
        if ("k" in schema.keys()):
            self.k = schema["k"]
        else:
            self.k = [1.0]   
        if ("rel_xo" in schema.keys()):
            self.rel_xo = schema["rel_xo"]
        else:
            self.rel_xo = [1.0]
        if ("x_o" in schema.keys()):
            self.x_o = schema["x_o"]
        else:
            self.x_o = [1.0]
        if ("rel_yo" in schema.keys()):
            self.rel_yo = schema["rel_yo"]
        else:
            self.rel_yo = None        
        if ("y_o" in schema.keys()):
            self.y_o = schema["y_o"]
        else:
            self.y_o = [1.0]
        if ("rel_zo" in schema.keys()):
            self.rel_zo = schema["rel_zo"]
        else:
            self.rel_zo = None
        if ("z_o" in schema.keys()):
            self.z_o = schema["z_o"]
        else:
            self.z_o = [1.0]
        if ("rel_x1" in schema.keys()):
            self.rel_x1 = schema["rel_x1"]
        else:
            self.rel_x1 = None
        if ("x_1" in schema.keys()):
            self.x_1 = schema["x_1"]
        else:
            self.x_1 = [1.0]
        if ("rel_y1" in schema.keys()):
            self.rel_y1 = schema["rel_y1"]
        else:
            self.rel_y1 = None
        if ("y_1" in schema.keys()):
            self.y_1 = schema["y_1"]
        else:
            self.y_1 = [1.0]
        if ("rel_z1" in schema.keys()):
            self.rel_z1 = schema["rel_z1"]
        else:
            self.rel_z1 = None
        if ("z_1" in schema.keys()):
            self.z_1 = schema["z_1"]
        else:
            self.z_1 = [1.0]
        if ("rel_x2" in schema.keys()):
            self.rel_x2 = schema["rel_x2"]
        else:
            self.rel_x2 = None
        if ("x_2" in schema.keys()):
            self.x_2 = schema["x_2"]
        else:
            self.x_2 = [1.0]
        if ("rel_y2" in schema.keys()):
            self.rel_y2 = schema["rel_y2"]
        else:
            self.rel_y2 = None
        if ("y_2" in schema.keys()):
            self.y_2 = schema["y_2"]
        else:
            self.y_2 = [1.0]
        if ("rel_z2" in schema.keys()):
            self.rel_z2 = schema["rel_z2"]
        else:
            self.rel_z2 = None
        if ("z_2" in schema.keys()):
            slf.z_2 = schema["z_2"]
        else:
            self.z_2 = [1.0]
        if ("data_k" in schema.keys()):
            self.data_k = schema["data_k"]
        else:
            self.data_k = [1.0]
        if ("data_o" in schema.keys()):
            self.data_o = schema["data_o"]
        else:
            self.data_o = [1.0]
        if ("data_1" in schema.keys()):
            self.data_1 = schema["data_1"]
        else:
            self.data_1 = [1.0]
        if ("data_2" in schema.keys()):
            self.data_2 = schema["data_2"]
        else:
            self.data_2 = [1.0]
    
    @classmethod
    def set_name(cls, value):
        cls.__name = value
        
    @property
    def fittype(self):
        return self.__type.name
    
    @fittype.setter
    def fittype(self, value):
        if self.Type.has_key(value.upper()):
            self.__type = self.Type[value.upper()]
        else:
            self.__type = self.Type.UNCLASSIFIED
            
    @property
    def system(self):
        return self.__system
    
    @system.setter
    def system(self, value):
        if value.lower == "return":
            self.__system = "Exhaust"
        elif (value.lower in ["suppy", "exhaust"]):
            self.__system = value.capitalize()
        else:
            self.__system = "Other"
    
    @property
    def description(self):
        return self.__description

    @description.setter
    def description(self, value):
        self.__description = value

    @property
    def rel_k(self):
        return self.__rel_k

    @rel_k.setter
    def rel_k(self, value):
        self.__rel_k = value
    
    @property
    def k(self):
        return self.__k
    
    @k.setter
    def k(self, value):
        self.__k = value
        
    @property
    def rel_xo(self):
        return self.__rel_xo
    
    @rel_xo.setter
    def rel_xo(self, value):
        self.__rel_xo = value
    
    @property
    def x_o(self):
        return self.__x_o
    
    @x_o.setter
    def x_o(self, value):
        self.__x_o = value
        
    @property
    def rel_yo(self):
        return self.__rel_yo
    
    @rel_yo.setter
    def rel_yo(self, value):
        self.__rel_yo = value
    
    @property
    def y_o(self):
        return self.__y_o
    
    @y_o.setter
    def y_o(self, value):
        self.__y_o = value
    
    @property
    def rel_zo(self):
        return self.__rel_zo
    
    @rel_zo.setter
    def rel_zo(self, value):
        self.__rel_zo = value
    
    @property
    def z_o(self):
        return self.__z_o
    
    @z_o.setter
    def z_o(self, value):
        self.__z_o = value
    
    @property
    def rel_x1(self):
        return self.__rel_x1
    
    @rel_x1.setter
    def rel_x1(self, value):
        self.__rel_x1 = value
    
    @property
    def x_1(self):
        return self.__x_1
    
    @x_1.setter
    def x_1(self, value):
        self.__x_1 = value
        
    @property
    def rel_y1(self):
        return self.__rel_y1
    
    @rel_y1.setter
    def rel_y1(self, value):
        self.__rel_y1 = value
    
    @property
    def y_1(self):
        return self.__y_1
    
    @y_1.setter
    def y_1(self, value):
        self.__y_1 = value
    
    @property
    def rel_z1(self):
        return self.__rel_z1
    
    @rel_z1.setter
    def rel_z1(self, value):
        self.__rel_z1 = value

    @property
    def z_1(self):
        return self.__z_1
    
    @z_1.setter
    def z_1(self, value):
        self.__z_1 = value
    
    @property
    def rel_x2(self):
        return self.__rel_x2
    
    @rel_x2.setter
    def rel_x2(self, value):
        self.__rel_x2 = value
    
    @property
    def x_2(self):
        return self.__x_2
    
    @x_2.setter
    def x_2(self, value):
        self.__x_2 = value
    
    @property
    def rel_y2(self):
        return self.__rel_y2
    
    @rel_y2.setter
    def rel_y2(self, value):
        self.__rel_y2 = value
    
    @property
    def y_2(self):
        return self.__y_2
    
    @y_2.setter
    def y_2(self, value):
        self.__y_2 = value
    
    @property
    def rel_z2(self):
        return self.__rel_z2
    
    @rel_z1.setter
    def rel_z2(self, value):
        self.__rel_z2 = value
    
    @property
    def z_2(self):
        return self.__z_2
    
    @z_2.setter
    def z_2(self, value):
        self.__z_2 = value
    
    @property
    def data_o(self):
        return self.__data_o
    
    @data_o.setter
    def data_o(self, value):
        self.__data_o = value
    
    @property
    def data_1(self):
        return self.__data_1
    
    @data_1.setter
    def data_1(self, value):
        self.__data_1 = value
        
    @property
    def data_2(self):
        return self.__data_2
    
    @data_2.setter
    def data_2(self, value):
        self.__data_2 = value
    
    @staticmethod
    def __get_interp_index(value:float, v_range:list) -> int:
        if v_range[0] > value:
            return 0
        for i in range(1, len(v_range)):
            if v_range[i] > value:
                return i - 1
        return len(v_range) - 2
    
    @classmethod
    def __interp1D(cls, value, x, data):
        if len(x) == 1:
            return data[0]
        i = cls.__get_interp_index(value, x)
        ratio = (value - x[i]) / (x[i + 1] - x[i])
        return (data[i] + ratio * (data[i + 1] - data[i]))
    
    @classmethod
    def __interp2D(cls, value, x, y, data):
        if len(y) == 1:
            return cls.__interp1D(value[0], x, data)
        i = cls.__get_interp_index(value[1], y)
        y_inf = cls.__interp1D(value[0], x, data[i])
        y_sup = cls.__interp1D(value[0], x, data[i + 1])
        return cls.__interp1D(value[1], [y[i], y[i + 1]], [y_inf, y_sup])
    
    @classmethod
    def __interp3D(cls, value, x, y, z, data):
        if len(z) == 1:
            return cls.__interp2D(value[:2], x, y, data)
        i = cls.__get_interp_index(value[1], y)
        z_inf = cls.__interp2D(value[:2], x, y, data[i])
        z_sup = cls.__interp2D(value[:2], x, y, data[i + 1])
        return cls.__interp1D(value[2], [z[i], z[i + 1]], [z_inf, z_sup])
    
    @staticmethod
    def __is_compatible(x, y, z, matrix):
        if len(z) == 1.0:
            if len(y) == 1.0:
                 return len(matrix) == len(x)
            else:
                 return len(matrix[0]) == len(x) and len(matrix) == len(y)
        else:
            return len(matrix[0][0] == len(x) and len(matrix[0]) == len(y) and len(matrix) == len(z))
    
    @staticmethod
    def __validate(schema:dict):
        """Validates if the given schema is compatible with the Fitting class
        
        Parameters:
        -----------
        schema -- dictionary to initialize a Fitting object
        
        Returns:
        --------
        True if schema is compatible, else False
        """
        try:
            if ("k" in schema.keys()):
                if (len(schema["k"]) != len(schema["data_k"])):
                    return False
            for j in ["o", "1", "2"]:
                if (("z_" + j) in schema.keys()):
                    if (not self.__is_compatible(schema["x_" + j],
                      schema["y_" + j], schema["z_" + j], schema["data_" + j])):
                        return False
                    elif ("y_" + j) in schema.keys():
                        if (not self.__is_compatible(schema["x_" + j],
                          schema["y_" + j], [1.0], schema["data_" + j])):
                            return False
                        elif (not self.__is_compatible(schema["x_" + j],
                          [1.0], [1.0], schema["data_" + j])):
                            return False
            return True
        except:
            return False
    
    @staticmethod
    def __get_value_of_rel(relation:str, condition:dict) -> float:
        """Calculates the value of a condition based in the relation rule
    
        Parameters:
        -----------
        relation --  string with the parameters relation either separated by '/', just a unique parameter or none
        condition -- dictionary with the conditions to evaluate the relation rule
    
        Returns:
        --------
        value obtained by operating the relation with the conditions
        """
        if (relation == None):
            return 1.0
        elif (len(relation.split("/")) == 1):
            return condition[relation]
        else:
            return condition[relation.split("/")[0]] / condition[relation.split("/")[1]]
       
    def get_Loss(self, condition:dict):
        """Converts a list with OSM contents in a list with lists of every object and another list with the types of these objects
    
        Parameters:
        -----------
        condition --  dictionary with physical conditions of fitting, example condition["A_i"] = 10
    
        Returns:
        --------
        loss coefficients in each output of fitting
        """
        val_k = self.__get_value_of_rel(self.rel_k, condition)
        val_xo = self.__get_value_of_rel(self.rel_xo, condition)
        val_yo = self.__get_value_of_rel(self.rel_yo, condition)
        val_zo = self.__get_value_of_rel(self.rel_zo, condition)
        val_o = [val_xo, val_yo, val_zo]
        val_x1 = self.__get_value_of_rel(self.rel_x1, condition)
        val_y1 = self.__get_value_of_rel(self.rel_y1, condition)
        val_z1 = self.__get_value_of_rel(self.rel_z1, condition)
        val_1 = [val_x1, val_y1, val_z1]
        val_x2 = self.__get_value_of_rel(self.rel_x2, condition)
        val_y2 = self.__get_value_of_rel(self.rel_y2, condition)
        val_z2 = self.__get_value_of_rel(self.rel_z2, condition)
        val_2 = [val_x2, val_y2, val_z2]
        result_k = self.__interp1D(val_k, self.k, self.data_k)
        result_o = result_k * self.__interp3D(val_o, self.x_o, self.y_o, self.z_o, self.data_o)
        result_1 = result_k * self.__interp3D(val_1, self.x_1, self.y_1, self.z_1, self.data_1)
        result_2 = result_k * self.__interp3D(val_2, self.x_2, self.y_2, self.z_2, self.data_2)
        if self.fittype == self.Type.UNCLASSIFIED:
            return result_o, result_1, result_2
        elif self.fittype == self.Type.TEE.name or self.fittype == self.Type.WYE.name:
            return result_o, result_1 
        else:
            return result_o
    
    def __str__(self):
        return self.__name
