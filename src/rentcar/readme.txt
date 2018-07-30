	AbstractCar类是这个继承体系中的最高父类，其中定义了各种子类车的共有变量及相应的 get()方法，以及一个抽象的 display()供各自类有不同的覆盖。

	PassengerCar,Truck，Pickup 三个类为AbstractCar的直接子类,加入了各自特有的变量，并根据各子类的属性集覆盖了display()方法。

	RentCars类是封装租车业务流程的类，有租车时形成的变量：租车天数，租车数量；还有对应整个租车业务各个流程的方法：如 展示所有汽车信息的 displayAll()，用户输入各车租用数量的 inputNums()方法等。

	TestRentCars类为调用RentCars实现租车业务的主类。






,

