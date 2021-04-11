import numpy as np

v = np.array([1, 2, 3], dtype='int64')
print(v)

m=np.array([[3.6,5.2,2.0],[1,2.3,5.1]])
print(m)

z=np.zeros((4,6))
print(' '*40)
print(z)
print(' '*40)
print(np.ones((5,5)))
print(' '*40)
print(np.full((2,2),3))
print(' '*40)
print(np.full_like(z,4))
print(' '*40)

n1=np.random.randint(-10,10,size=(4,5))
n2=np.random.randint(-10,10,size=(4,5))

print(n1)
print(n1[1,2])
print(n1[0,0])
print(n1[:,2])

n1[3,:]=[0.3,0.2,1,3.4,2.2]
print(n1)

v1=np.random.randint(-10,10,size=20)
v2=np.random.randint(-10,10,size=20)
print(v1[9:28:3]) #co trzeci zaczynając od 9 elementu
print(v2[v1>0])
#np.matmul(m1,m2) mnożenie macierzy
print(' '*40)
print(n1)
print(' '*40)
print(n1.reshape(5,4))