package com.banyan.FullLoadRequest.models.enums;

public enum EquipmentTypes {
	Trailer_28ft ("Trailer – 28ft", 0),
	Other("Other",1), 
	Trailer_48ft ("Trailer – 48ft", 2),
	Tanker("Tanker", 3),
	RailCar_20ft( "Rail Car - 20ft", 4),
	VanTrailer_Standard("Van-standard trailer",5),
	VanTrailer_53ft ("53ft - Van Trailer", 6),
	Trailer_26ft("Trailer – 26ft",7),
	Dumper_60yd ("Dumper - 60 yard", 8),
	CarCarrier("Car-Carrier",9), 
	DoubleDrop ("Double Drop", 10),
	RailCar_40ft("Rail Car - 40ft",11), 
	Dumper_80yd("Dumper - 80 yard", 12),
	StraightTruck_24ft_LiftGate("24ft Straight Truck w/ lift gate",13),
	Trailer_24ft ("Trailer – 24ft", 14),
	StraightTruck_24ft("24ft Straight Truck",15),
	Container_45ft("Container - 45ft", 16),
	Truck_4by4("4x4",17), 
	StraightTruck_26ft_LiftGate("26ft Straight Truck w/ lift gate", 18),
	DumpTruck("Dump Truck",19), 
	Gooseneck("Gooseneck", 20),
	Dumper_40yd("Dumper - 40 yard",21),
	Van ("Van", 22),
	Container_40HQ("Container - 40HQ",23), 
	Truck ("Truck", 24),
	SprinterVan("Sprinter Van",25), 
	HopperBottom ("Hopper Bottom", 26),
	Car("Car",27), 
	StraightTruck_26ft ("26ft Straight Truck", 28),
	Dumper_20yd("Dumper - 20 yard",29),
	AirCargo ("Air Cargo", 30),
	MaxiTrailer("Maxi Trailer",31), 
	Frozen ("Frozen", 32),
	BoxTruck_LiftGate("Box truck w/ lift gate",33),
	Van_LiftGate("Van w/ lift gate", 34),
	StepDeck("Step Deck",35), 
	Truck_LiftGate("Truck w/ lift gate", 36),
	RollOff("Roll-off Truck",37), 
	Container_53ft("Container - 53ft", 38),
	Container_20ft("Container - 20ft",39),
	Refrigerated("Refrigerated", 40),
	WalkingFloor("Walking Floor",41), 
	Container_40ft("Container - 40ft", 42),
	RailCar_53ft("Rail Car - 53ft",43),
	LowBoy("Low Boy", 44),
	FlatBed("Flat Bed",45), 
	PickupTruck("Pickup Truck", 46),
	BoxTruck("Box truck",47), 
	ParcelTruck("Parcel Truck", 48),
	Van_TriAxle("Tri-Axle Van",49);
	
	
	private final String name;
	private final int value;

	private EquipmentTypes(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
