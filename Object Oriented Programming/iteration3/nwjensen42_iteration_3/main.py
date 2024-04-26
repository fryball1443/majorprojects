from classes.Event_Manager import EventManager
from UI.GUI import Gui

# This imports the contacts.json and events.json which hold all of the event and contact info.
import json

with open('contacts.json') as f:
    contact_data = json.load(f)
with open('events.json') as g:
    event_data = json.load(g)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    #initialize the EventManager object (there's only 1 instance of it)
    em = EventManager()

    #load pre-existing contacts into em (from 'contacts.json')
    for x in contact_data:
        em.add_contact(x)

    # add all events from the events.json into the event manager
    for x in event_data["university_events"]:
        em.add_event(x)

    # initialize the gui object (from tkinter) and pass the em object into it
    gui = Gui(em)
