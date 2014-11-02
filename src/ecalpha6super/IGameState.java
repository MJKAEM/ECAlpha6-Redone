package ecalpha6super;


public interface IGameState
{
	void Draw();

	void KeyPressed();

	void KeyReleased();

	int ReturnID();

	void Update();
}
