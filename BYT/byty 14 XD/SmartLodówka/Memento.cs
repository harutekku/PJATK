using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    class Memento
    {
        public Dictionary<Ingredients, int> Dictionary;
        public Memento(Dictionary<Ingredients, int> dictionary)
        {
            Dictionary = dictionary;
        }
        public Dictionary<Ingredients, int> getMemento()
        {
            return Dictionary;
        }
    }
}
