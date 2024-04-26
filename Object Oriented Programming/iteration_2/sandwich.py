

class Sandwich:
  def __init__(self, order_num, bread, meat):
    self.order_num = order_num
    self.bread = bread
    self.meat = meat
    self.toppings = []
  
  def get_order_num(self):
    return self.order_num
  
  def get_bread_type(self):
    return self.bread
  
  def get_meat_type(self):
    return self.meat
  
  def add_topping(self, topping):
    self.toppings.append(topping)
  
  def remove_topping(self, topping):
    if topping in self.toppings:
      self.toppings.remove(topping)
  
  def get_price(self):
    return 3.75 + (0.5 * len(self.toppings))
  
  def info(self):
    print("order number:", self.order_num)
    print("bread:", self.bread)
    print("meat:", self.meat)
    print("toppings:", self.toppings)
    print("price: $", self.get_price())
    print()

class Meal(Sandwich):
  def __init__(self, order_num, bread, meat, drink, side):
    super().__init__(order_num, bread, meat)
    self.drink = drink
    self.side = side
  
  def get_drink(self):
    return self.drink
  
  def set_drink(self, drink):
    self.drink = drink
  
  def get_side(self):
    return self.side
  
  def set_side(self, side):
    self.side = side
  
  def get_price(self):
    return 6.75 + (0.5 * len(self.toppings))
  
  def info(self):
    print("order number:", self.order_num)
    print("bread:", self.bread)
    print("meat:", self.meat)
    print("toppings:", self.toppings)
    print("drink:", self.drink)
    print("side:", self.side)
    print("price: $", self.get_price())
    print()


class KidsMeal(Meal):
  def __init__(self, order_num, bread, meat, drink, side, toy):
    super().__init__(order_num, bread, meat, drink, side)
    self.toy = toy
  
  def get_toy(self):
    return self.toy
  
  def set_toy(self, toy):
    self.toy = toy
  
  def get_price(self):
    return 4.75 + (0.3 * len(self.toppings))
  
  def info(self):
    print("order number:", self.order_num)
    print("bread:", self.bread)
    print("meat:", self.meat)
    print("toppings:", self.toppings)
    print("drink:", self.drink)
    print("side:", self.side)
    print("toy:", self.toy)
    print("price: $", self.get_price())
    print()

def main():
  meal_type = input("Enter meal type (sandwich, meal, kids meal): ")
  order_num = input("Enter order number: ")
  bread = input("Enter bread type: ")
  meat = input("Enter meat type: ")
  if meal_type == "sandwich":
    sandwich = Sandwich(order_num, bread, meat)
    topping = input("Enter topping (or 'done' to finish): ")
    while topping != "done":
      if topping.startswith("-"):
        sandwich.remove_topping(topping[1:])
      else:
        sandwich.add_topping(topping)
      topping = input("Enter topping (or 'done' to finish): ")
    sandwich.info()
  elif meal_type == "meal":
    drink = input("Enter drink: ")
    side = input("Enter side: ")
    meal = Meal(order_num, bread, meat, drink, side)
    topping = input("Enter topping (or 'done' to finish): ")
    while topping != "done":
      if topping.startswith("-"):
        meal.remove_topping(topping[1:])
      else:
        meal.add_topping(topping)
      topping = input("Enter topping (or 'done' to finish): ")
    meal.info()
  elif meal_type == "kids meal":
    drink = input("Enter drink: ")
    side = input("Enter side: ")
    toy = input("Enter toy: ")
    kids_meal = KidsMeal(order_num, bread, meat, drink, side, toy)
    topping = input("Enter topping (or 'done' to finish): ")
    while topping != "done":
      if topping.startswith("-"):
        kids_meal.remove_topping(topping[1:])
      else:
        kids_meal.add_topping(topping)
      topping = input("Enter topping (or 'done' to finish): ")
    kids_meal.info()
  else:
    print("Invalid meal type. please try again.")

if __name__ == "__main__":
  main()









