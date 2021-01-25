using System;
using System.Collections.Generic;
using System.Text;

namespace SmartLodówka
{
    interface State
    {
        void open();
        void close();
        Dish makeDish(Contents Contents, Packer packer, string Name);
        void defrost();
    }
}
