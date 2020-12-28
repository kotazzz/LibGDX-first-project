package ru.newlife.gdxproject;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;

import ru.newlife.gdxproject.declaration.BlockList;
import ru.newlife.gdxproject.world.Block;

public class Main extends ApplicationAdapter {
    public Environment environment;
	public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public ModelInstance instance;
    public ModelInstance instance2;
    public ModelInstance instance3;
    public Vector3 position = new Vector3(10f, 10f, 10f);
    public Vector3 direction = new Vector3(0f,0f,-1f);
    public float movespeed = 1f;
    public float cameraspeed = 0.004f;
    
    
    
	@Override
	public void create () {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        modelBatch = new ModelBatch();
        
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.lookAt(0,0,0);
        cam.near = 0f;
        cam.far = 300f;
        cam.update();
        
        Block gr = new Block(BlockList.blocks.get("grass"));
        Block st = new Block(BlockList.blocks.get("grass"));
        Block bd = new Block(BlockList.blocks.get("grass"));
        gr.setPosition(0, 0, 0);
        st.setPosition(1, 1, 1);
        bd.setPosition(1, 2, 1);
        instance = gr.instance;
        instance2 = st.instance;
        instance3 = bd.instance;
        
	}
	
	public void update() {
		if(Gdx.input.isKeyPressed(Keys.D)) {
			position.x+= movespeed;
		}

		if(Gdx.input.isKeyPressed(Keys.A)) {
			position.x-= movespeed;
		}

		if(Gdx.input.isKeyPressed(Keys.W)) {
			position.z-= movespeed;
		}

		if(Gdx.input.isKeyPressed(Keys.S)) {
			position.z+= movespeed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			position.y += movespeed;
		}
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			position.y-= movespeed;
		}
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			direction.x = - Gdx.input.getDeltaX() * cameraspeed;
			direction.y =   Gdx.input.getDeltaY() * cameraspeed;
		} else {
			direction.x = 0;
			direction.y = 0;
		}
		
		
	}
	@Override
	public void render () {
		//instance.transform.translate(0.1f,0.1f,0.1f);
		update();
		cam.position.set(position);
		cam.direction.add(direction.x, direction.y, 0);
		cam.up.set(0, 1, 0);
		cam.update();
		Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        modelBatch.begin(cam);
        modelBatch.render(instance,environment);
        modelBatch.render(instance2,environment);
        modelBatch.render(instance3,environment);
        modelBatch.end();
	}
	
	@Override
	public void dispose () {
	}
}
