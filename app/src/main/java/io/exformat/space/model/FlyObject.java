package io.exformat.space.model;

public class FlyObject
{

	private double M = 110;

	private double radius = 50;

	private double dryMass = 10;
	private float fuelMass = 100;
	private float fuelOut = 0.1f;
	private float powerTrust = 100;
	
	private double x = 0;
	private double y = 0;
	private double z = 0;

	private double Vx = 0;
	private double Vy = 0;
	private double Vz = 0;

	private float angleDirectXY = 0;
	private float angleDirectYZ = 0;
	
	private String name = "";
	
	public FlyObject(){}

	public FlyObject(double x,
					 double y,
					 double z,
					 double M,
					 double Vx,
					 double Vy,
					 double Vz,
					 String name
				   )
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.M = M;
		this.Vx = Vx;
		this.Vy = Vy;
		this.Vz = Vz;
		this.name = name;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public float getAngleDirectXY() {
		return angleDirectXY;
	}

	public void setAngleDirectXY(float angleDirectXY) {
		this.angleDirectXY = angleDirectXY;
	}

	public float getAngleDirectYZ() {
		return angleDirectYZ;
	}

	public void setAngleDirectYZ(float angleDirectYZ) {
		this.angleDirectYZ = angleDirectYZ;
	}

	public double getDryMass() {
		return dryMass;
	}

	public void setDryMass(double dryMass) {
		this.dryMass = dryMass;
	}

	public void setPowerTrust(float powerTrust)
	{
		this.powerTrust = powerTrust;
	}

	public float getPowerTrust()
	{
		return powerTrust;
	}

	public void setFuelOut(float fuelOut)
	{
		this.fuelOut = fuelOut;
	}

	public float getFuelOut()
	{
		return fuelOut;
	}

	public void setFuelMass(float fuelMass)
	{
		this.fuelMass = fuelMass;
	}

	public float getFuelMass()
	{
		return fuelMass;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setVz(double vz)
	{
		Vz = vz;
	}

	public double getVz()
	{
		return Vz;
	}

	public void setVy(double vy)
	{
		Vy = vy;
	}

	public double getVy()
	{
		return Vy;
	}

	public void setVx(double vx)
	{
		Vx = vx;
	}

	public double getVx()
	{
		return Vx;
	}

	public void setM(double m)
	{
		M = m;
	}

	public double getM()
	{
		return M;
	}

	public void setZ(double z)
	{
		this.z = z;
	}

	public double getZ()
	{
		return z;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getY()
	{
		return y;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getX()
	{
		return x;
	}}
