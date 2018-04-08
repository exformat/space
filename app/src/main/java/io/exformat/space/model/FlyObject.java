package io.exformat.space.model;


import java.util.ArrayList;

public class FlyObject
{

	private boolean bombActivated = false;
	private boolean bombExplosive = false;
	private int drawBombExplosiveTick;
	private ArrayList<Vector3> bombFragments = new ArrayList<>();

	private double healthPoints = 100;

	private double mass = 110;

	private double radius = 50;

	private double dryMass = 10;
	private float fuelMass = 100;
	private float fuelOut = 0.1f;
	private double powerTrust = 100;

	private double x = 0;
	private double y = 0;
	private double z = 0;

	private double Vx = 0;
	private double Vy = 0;
	private double Vz = 0;

	private float angleDirectXY = 0;
	private float angleDirectYZ = 0;

	private float angleSpeedXY = 0;
	private float angleSpeedYZ = 0;

	private String name = "";

	public FlyObject(){}

	public FlyObject(double x,
					 double y,
					 double z,
					 double Vx,
					 double Vy,
					 double Vz,
					 double powerTrust)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.Vx = Vx;
		this.Vy = Vy;
		this.Vz = Vz;
		this.powerTrust = powerTrust;
	}

	public int getDrawBombExplosiveTick() {
		return drawBombExplosiveTick;
	}

	public void setDrawBombExplosiveTick(int drawBombExplosiveTick) {
		this.drawBombExplosiveTick = drawBombExplosiveTick;
	}

	public ArrayList<Vector3> getBombFragments() {
		return bombFragments;
	}

	public void setBombFragments(ArrayList<Vector3> bombFragments) {
		this.bombFragments = bombFragments;
	}

	public boolean getBombExplosive() {
		return bombExplosive;
	}

	public void setBombExplosive(boolean bombExplosive) {
		this.bombExplosive = bombExplosive;
	}

	public void setPowerTrust(double powerTrust) {
		this.powerTrust = powerTrust;
	}

	public double getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(double healthPoints) {
		this.healthPoints = healthPoints;
	}

	public boolean getBombActivated() {
		return bombActivated;
	}

	public void setBombActivated(boolean bombActivated) {
		this.bombActivated = bombActivated;
	}

	public float getAngleSpeedXY() {
		return angleSpeedXY;
	}

	public void setAngleSpeedXY(float angleSpeedXY) {
		this.angleSpeedXY = angleSpeedXY;
	}

	public float getAngleSpeedYZ() {
		return angleSpeedYZ;
	}

	public void setAngleSpeedYZ(float angleSpeedYZ) {
		this.angleSpeedYZ = angleSpeedYZ;
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

	public double getPowerTrust()
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


	public void setFuelMass(float fuelMass) {
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


	public void setMass(double mass)
	{
		this.mass = mass;
	}

	public double getMass()
	{
		return mass;
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
