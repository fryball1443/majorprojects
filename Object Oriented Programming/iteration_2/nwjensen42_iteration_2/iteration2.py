import json

class Contact:
    def __init__(self, first_name, last_name, user_id, email, department, job_title, phone_number, building, po_box):
        self.first_name = first_name
        self.last_name = last_name
        self.user_id = user_id
        self.email = email
        self.department = department
        self.job_title = job_title
        self.phone_number = phone_number
        self.building = building
        self.po_box = po_box

class Event:
    def __init__(self, event_name, event_id, event_date, start_time, location, duration):
        self.event_name = event_name
        self.event_id = event_id
        self.event_date = event_date
        self.start_time = start_time
        self.location = location
        self.duration = duration

def view_contacts():
    for contact in contacts:
        print("Contact Information:")
        print(f"First Name: {contact.first_name}")
        print(f"Last Name: {contact.last_name}")
        print(f"User ID: {contact.user_id}")
        print(f"Email: {contact.email}")
        print(f"Department: {contact.department}")
        print(f"Job Title: {contact.job_title}")
        print(f"Phone Number: {contact.phone_number}")
        print(f"Building: {contact.building}")
        print(f"PO Box: {contact.po_box}")
        print()

def view_events():
    for event in events:
        print("Event Information:")
        print(f"Event Name: {event.event_name}")
        print(f"Event ID: {event.event_id}")
        print(f"Event Date: {event.event_date}")
        print(f"Start Time: {event.start_time}")
        print(f"Location: {event.location}")
        print(f"Duration: {event.duration}")
        print()

def input_last_communication_date():
    user_id = input("Enter the user ID of the contact: ")
    for contact in contacts:
        if str(contact.user_id) == str(user_id):
            print(f"\n Contact: {contact.first_name} {contact.last_name}")
            last_communication_date = input("Enter the last date of communication: ")
            # Update the contact object with the last communication date
            contact.last_communication_date = last_communication_date
            break
    else:
        print("Contact not found.")

def associate_action_items():
    event_id = input("Enter the event ID: ")
    for event in events:
        if str(event.event_id) == str(event_id):
            print(f"\n Event: {event.event_name}")
            action_items = input("Enter the action items (separated by commas): ").split(",")
            # Update the event object with the action items
            event.action_items = action_items
            break
    else:
        print("Event not found.")

def menu():
    while True:
        print("Menu:")
        print("1. View contacts")
        print("2. View events")
        print("3. Input last date of communication for a contact")
        print("4. Associate action items with an event")
        print("5. Exit")

        choice = input("Enter your choice: ")

        if choice == "1":
            view_contacts()
        elif choice == "2":
            view_events()
        elif choice == "3":
            input_last_communication_date()
        elif choice == "4":
            associate_action_items()
        elif choice == "5":
            break
        else:
            print("Invalid choice. Please try again.")

with open('contacts.json') as f:
    contact_data = json.load(f)

contacts = []
for contact in contact_data:
    contacts.append(Contact(contact['FirstName'], contact['LastName'], contact['UID'], contact['EmailAddress'], contact['Dept'], contact['Title'], contact['Phone'], contact['Building'], contact['POBox']))

with open('events.json') as g:
    event_data = json.load(g)

events = []
for event in event_data["university_events"]:
    events.append(Event(event['Name'], event['UID'], event['Date'], event['StartTime'], event['Location'], event['Duration']))

while True:
    menu()