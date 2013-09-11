package bomb.enemies;

public enum BossType {
	PEACH(3000, 32);

	int hp;
	int size;

	BossType(int hp, int size) {
		this.hp = hp;
		this.size = size;
	}
}