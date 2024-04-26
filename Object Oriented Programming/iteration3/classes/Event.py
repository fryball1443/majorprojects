"""
This is the Event class that holds all the event info ie name, date, location etc for a SINGLE event

A dictionary is passed into the constructor to populate the attributes

The Event_Manager class will have a list of these Event objects

"""
class Event(object):
    # pass a dictionary into the constructor to populate the values
    def __init__(self, data: dict):
        self.__name: str = data["Name"]
        self.__date: str = data["Date"]
        self.__UID: int = data["UID"]
        self.__start_time: str = data["StartTime"]
        self.__location: str = data["Location"]
        self.__duration: str = data["Duration"]

    #getters for each attribute
    @property
    def name(self):
        return self.__name

    @property
    def UID(self):
        return self.__UID

    @property
    def date(self):
        return self.__date

    @property
    def start_time(self):
        return self.__start_time

    @property
    def location(self):
        return self.__location

    @property
    def duration(self):
        return self.__duration

    # This function defines what happens when you print the object as text ie print(Event)
    def __str__(self):
        """
        python has a few ways of streamlining concatenation of strings.
        each time there's a {} in the string, that represents a variable.
        notice at the end of the string, ".format()"
        the variables passed into this function will replace each {} (in order)
        """
        return "Event: {}\nDate: {}\nStart time: {}\nDuration: {} hours\nLocation: {}"\
            .format(self.name, self.date, self.start_time, self.duration, self.location)