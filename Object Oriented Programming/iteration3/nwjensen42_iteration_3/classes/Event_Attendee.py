from classes.Contact import Contact
from classes.Event import Event

"""
This class represents a tuple of 1 Event and 1 Contact object, meaning the Contact is attending the Event.
This is its own class because there will be attributes associated to a contact attending an event ie
whether they need a parking pass, special accommodations, etc. 

For now, the "memo" attribute is a catch-all for these, but in the future, more attributes will be added
"""

class Event_Attendee(object):
    # an Event object and Contact object are passed into the constructor, essentially creating a tuple object
    def __init__(self, e: Event, c: Contact):
        self.__event = e
        self.__contact = c

    @property
    def event(self):
        return self.__event

    @event.setter
    def event(self, e: Event):
        self.__event = e

    @property
    def contact(self):
        return self.__contact

    @contact.setter
    def contact(self, c: Contact):
        self.__contact = c

    # This function defines what happens when you print the object as text ie print(Event_Attendee)
    # PRINTS IN THE FORM "John Smith attending Data Science League Meeting"
    def __str__(self):
        """
        python has a few ways of streamlining concatenation of strings.
        each time there's a {} in the string, that represents a variable.
        notice at the end of the string, ".format()"
        the variables passed into this function will replace each {} (in order)
        """
        return "{} {}\nattending\n{}".format(self.contact.firstname, self.contact.lastname, self.event.name)