using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    class Contents
    {
        public Dictionary<Ingredients, int> Dictionary;
        public List<Memento> Memento;
        public Contents()
        {
            Dictionary = new Dictionary<Ingredients, int>();
            Dictionary.Add(new Ingredients("Bułka"), 5);
            Dictionary.Add(new Ingredients("Masło"), 5);
            Dictionary.Add(new Ingredients("Szynka"), 2);
            Dictionary.Add(new Ingredients("Ser"), 5);
            Dictionary.Add(new Ingredients("Ketchup"), 1);
            Dictionary.Add(new Ingredients("Rzodkiewka"), 5);
            Memento = new List<Memento>();
            Memento.Add(new Memento(Dictionary));
        }
        public bool checkIngredient(Ingredients name)
        {
            foreach (var item in Dictionary.Keys)
            {
                if (item.Name.Equals(name.Name))
                {
                    if (Dictionary[item] == 0) return false;
                    Dictionary[item]--;
                    return true;
                }
            }
            return false;
        }
        public bool prepareIngredients(List<Ingredients> list)
        {
            for (int i = 0; i < list.Count; i++)
            {
                if (!checkIngredient(list[i]))//brakuje składników
                {
                    Dictionary = Memento[Memento.Count - 1].Dictionary;
                    return false;
                }
            }
            Memento.Add(new Memento(Dictionary));
            return true;
        }
        public void addIngredient(Ingredients ingredients)
        {
            bool contains = false;
            foreach (var item in Dictionary.Keys)
            {
                if (item.Name.Equals(ingredients.Name))
                {
                    contains = true;
                    Dictionary[item]++;
                }
            }
            if (!contains)
            {
                Dictionary.Add(ingredients, 1);
            }
            Memento.Add(new Memento(Dictionary));
        }
    }
}
