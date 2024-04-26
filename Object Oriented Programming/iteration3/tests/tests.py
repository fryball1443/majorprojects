from unittest import TestCase
from classes.Contact import Contact
from classes.Event import Event
from classes.Event_Attendee import Event_Attendee
from classes.Event_Manager import EventManager

class TestContact(TestCase):
    def setUp(self):
        self.contact_data = {
            "FirstName": "Rajesh",
            "LastName": "Manicavasagam",
            "UID": 17,
            "EmailAddress": "rmanicavasagam@tntech.edu",
            "Dept": "Computer Science",
            "Title": "Instructor",
            "Phone": "931-372-6127",
            "Building": "Prescott Hall (PRSC) 406",
            "POBox": "5101"
            }
        self.event_data = {
            "Name": "Career Fair",
            "UID": 1,
            "Date": "2023-11-05",
            "StartTime": "10:00",
            "Location": "Student Center",
            "Duration": 6
            }

    def test_contact(self):
        self.contact = Contact(self.contact_data)

        self.assertEqual(self.contact.firstname, "Rajesh")
        self.assertEqual(self.contact.lastname, "Manicavasagam")

    def test_event(self):
        self.event = Event(self.event_data)

        self.assertEqual(self.event.name, "Career Fair")

    #TODO hollow these out
    def test_event_attendee(self):
        self.attendee = Contact(self.contact_data)
        self.event_attended = Event(self.event_data)
        self.event_attendee = Event_Attendee(self.event_attended, self.attendee)

        self.assertEqual(self.event_attendee.event.name, "Career Fair")
        self.assertEqual(self.event_attendee.contact.firstname, "Rajesh")

    #TODO hollow these out
    def test_event_manager(self):
        self.event_manager = EventManager()
        self.event_manager.add_contact(self.contact_data)
        self.event_manager.add_event(self.event_data)
        self.event_manager.add_event_attendee(self.event_manager.events[0], self.event_manager.contacts[0])

        self.assertEqual(self.event_manager.contacts[0].firstname, "Rajesh")
        self.assertEqual(self.event_manager.events[0].name, "Career Fair")
        self.assertIsInstance(self.event_manager.event_attendees[0].contact, Contact)