import unittest
from sandwich import Sandwich, Meal, KidsMeal

class TestSandwich(unittest.TestCase):
    def test_sandwich(self):
        sandwich = Sandwich(1, "wheat", "turkey")
        sandwich.add_topping("lettuce")
        sandwich.add_topping("tomato")
        self.assertEqual(sandwich.get_id(), 1)
        self.assertEqual(sandwich.get_bread_type(), "wheat")
        self.assertEqual(sandwich.get_meat_type(), "turkey")
        self.assertEqual(sandwich.get_toppings(), ["lettuce", "tomato"])

class TestMeal(unittest.TestCase):
    def test_meal(self):
        meal = Meal(2, "white", "ham", "soda", "chips")
        meal.add_topping("mayo")
        self.assertEqual(meal.get_id(), 2)
        self.assertEqual(meal.get_bread_type(), "white")
        self.assertEqual(meal.get_meat_type(), "ham")
        self.assertEqual(meal.get_drink(), "soda")
        self.assertEqual(meal.get_side(), "chips")
        self.assertEqual(meal.get_toppings(), ["mayo"])

class TestKidsMeal(unittest.TestCase):
    def test_kids_meal(self):
        kids_meal = KidsMeal(3, "white", "pb&j", "juice", "apple slices", "toy car")
        kids_meal.add_topping("jelly")
        self.assertEqual(kids_meal.get_id(), 3)
        self.assertEqual(kids_meal.get_bread_type(), "white")
        self.assertEqual(kids_meal.get_meat_type(), "pb&j")
        self.assertEqual(kids_meal.get_drink(), "juice")
        self.assertEqual(kids_meal.get_side(), "apple slices")
        self.assertEqual(kids_meal.get_toy(), "toy car")
        self.assertEqual(kids_meal.get_toppings(), ["jelly"])

if __name__ == '__main__':
    unittest.main()