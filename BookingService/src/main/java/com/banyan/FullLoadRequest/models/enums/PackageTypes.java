package com.banyan.FullLoadRequest.models.enums;

public enum PackageTypes {

	
	Accessorials(0),Attachments(1),Bags(2),Bales(3), Baskets(4), Bulkheads(5),Bundles(6),Boxes(7),Barrels(8),Buckets(9),
	Carboys(10),Cases(11),Chests(12),Coils(13),Cartons(14),Crates(15),Container(16),Cylinders(17), Drums(18), Envelopes(19),
	Feet(20),Firkins(21),Gaylords(22),Kegs(23),Loose(24),Octabin(25),Packages(26),Pails(27),Pieces(28),Pallets(29), Racks(30),
	Reels(31),Rolls(32),Skids(33),Slipsheets(34),SuperSack(35), Totes(36),Truckload(37),Trailers(38),Trunks(39),Trays(40),
	Tubes(41),Units(42),Unpackaged(43),Vehicles(44),Bins(45);
	
	private final int value;
	
	private PackageTypes(int packageCode) {
		this.value = packageCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
