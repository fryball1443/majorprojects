# The Contact class represents a single person wth attributes like "firstname" "lastname" "email" etc.

# A Contact object is created by passing a dictionary into the constructor and populating its attributes with the dictionary data

# The Event_Manager class will contain a list of Contact objects (a list of people)

class Contact(object):
    # a dictionary is passed into the constructor and all of the attributes are initialized using the dictionary data
    def __init__(self, data: dict):
        self.__lastname: str = data["LastName"]
        self.__firstname: str = data["FirstName"]
        self.__UID: int = data["UID"]
        if "MiddleName" in data:
            self.__middlename: str = data["MiddleName"]
        self.__email: str = data["EmailAddress"]
        self.__department: str = data["Dept"]
        self.__title: str = data["Title"]
        self.__phone: str = data["Phone"]
        self.__building: str = data["Building"]
        if "POBOX" in data:
            self.__mailcode: str = data["POBOX"]
        if "LastContact" in data:
            self.__lastcontact = data["LastContact"]
        else:
            self.__lastcontact = ""

    # getters for each attribute
    @property
    def lastname(self):
        return self.__lastname

    @property
    def firstname(self):
        return self.__firstname

    @property
    def UID(self):
        return self.__UID

    @property
    def email(self):
        return self.__email

    @property
    def department(self):
        return self.__department

    @property
    def title(self):
        return self.__title

    @property
    def phone(self):
        return self.__phone

    @property
    def building(self):
        return self.__building

    @property
    def mailcode(self):
        return self.__mailcode

    @property
    def lastcontact(self):
        return self.__lastcontact

    @lastcontact.setter
    def lastcontact(self, lc: str):
        self.__lastcontact = lc

    # This function defines what happens when you print the object as text ie print(Contact)
    def __str__(self):
        """
        python has a few ways of streamlining concatenation of strings.
        each time there's a {} in the string, that represents a variable.
        notice at the end of the string, ".format()"
        the variables passed into this function will replace each {} (in order)
        """
        return "{} {}\nTitle: {}\nEmail: {}\nDepartment: {}\nPhone: {}\n"\
               "Building: {}".format(self.firstname,
                                                   self.lastname,
                                                   self.title,
                                                   self.email,
                                                   self.department,
                                                   self.phone,
                                                   self.building,
                                                   )