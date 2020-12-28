package ru.newlife.gdxproject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class Main extends ApplicationAdapter {
    public Environment environment;
	public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public Model model;
    public Model model2;
    public ModelInstance instance;
    public ModelInstance instance2;
    public Vector3 position = new Vector3(10f, 10f, 10f);
    public Vector3 direction = new Vector3(0f,0f,0f);
    public float movespeed = 1f;
    public float cameraspeed = 0.01f;
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

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(10f, 10f, 10f, 
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                Usage.Position | Usage.Normal);
        model2 = modelBuilder.createBox(10f, 20f, 10f, 
                new Material(ColorAttribute.createDiffuse(Color.YELLOW)),
                Usage.Position | Usage.Normal);
        instance = new ModelInstance(model);
        instance2 = new ModelInstance(model2);
        
	}
	
	public float x = 0;
	public float y = 0;
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
			direction.y = - Gdx.input.getDeltaX();
			direction.x = - Gdx.input.getDeltaY();
			
		} else {
			direction.x = 0;
			direction.y = 0;
		}
		
		
	}
	@Override
	public void render () {
		update();
		cam.position.set(position);
		cam.rotateAround(position, direction, (direction.x+direction.y)/3);
		cam.update();
		System.out.println("dx: "+direction.x+" dy: "+direction.y+" x: "+position.x+" y: "+position.y+" z: "+position.z);
		
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        modelBatch.begin(cam);
        modelBatch.render(instance2, environment);
        modelBatch.end();
	}
	
	@Override
	public void dispose () {
        model.dispose();
	}
}
