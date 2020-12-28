package ru.newlife.gdxproject;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class Main extends ApplicationAdapter {
    public Environment environment;
	public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public Model model;
    public ModelInstance instance;
    public Vector3 position = new Vector3(10f, 10f, 10f);
    public Vector3 direction = new Vector3(0f,0f,-1f);
    public float movespeed = 1f;
    public float cameraspeed = 0.004f;
    
    public float sizemultiplr = 10;
    public int texture_size = 16;
    
    public  Map<String, TextureRegion> textureRegions = new HashMap<String, TextureRegion>();
    class BlockTexture {
    	TextureRegion F;
    	TextureRegion L;
    	TextureRegion R;
    	TextureRegion B;
    	TextureRegion U;
    	TextureRegion D;
    	
    	BlockTexture(
    			TextureRegion F,
    			TextureRegion L,
    			TextureRegion R,
    			TextureRegion B,
    			TextureRegion U,
    			TextureRegion D
    			) {
    		this.F = F;
    		this.L = L;
    		this.R = R;
    		this.B = B;
    		this.U = U;
    		this.D = D;
    		
    	}
    }
    public BlockTexture genearateTexture(TextureRegion sixsideblock) {
    	Texture atlas = sixsideblock.getTexture();
        TextureRegion textureonesplited[][] = TextureRegion.split( atlas,  atlas.getWidth() / 5*6,  atlas.getHeight() / 40);
        int x = sixsideblock.getRegionX();
        int y = sixsideblock.getRegionY();
        BlockTexture texture = new BlockTexture(
        		new TextureRegion(atlas, x+texture_size*0, y+texture_size*1, texture_size, texture_size),
        		new TextureRegion(atlas, x+texture_size*1, y+texture_size*2, texture_size, texture_size),
        		new TextureRegion(atlas, x+texture_size*2, y+texture_size*3, texture_size, texture_size),
        		new TextureRegion(atlas, x+texture_size*3, y+texture_size*4, texture_size, texture_size),
        		new TextureRegion(atlas, x+texture_size*4, y+texture_size*5, texture_size, texture_size),
        		new TextureRegion(atlas, x+texture_size*5, y+texture_size*6, texture_size, texture_size)
        		);
     return texture;
        
    }
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
        Texture atlas = new Texture("textures/block/atlas1.png");
        TextureRegion textureslpited[][] = TextureRegion.split( atlas,  atlas.getWidth() / 5,  atlas.getHeight() / 40);
        textureRegions.put("block1", textureslpited[0][0]);
        textureRegions.put("brick1", textureslpited[0][1]);
        textureRegions.put("brick2", textureslpited[1][0]);
        textureRegions.put("brick3", textureslpited[1][1]);
        TextureAttribute texat = TextureAttribute.createDiffuse(textureRegions.get("brick1"));
        Material m = new Material(texat);
        long Attr = VertexAttributes.Usage.Position |
                VertexAttributes.Usage.TextureCoordinates |
                VertexAttributes.Usage.Normal;
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
    			TextureAttribute.createDiffuse(genearateTexture(textureRegions.get("block1")).R)))
        .rect(-0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, 
        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, 
        		-0.5f*sizemultiplr, -0.5f*sizemultiplr, 0f, 0f, -1f*sizemultiplr);
        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
    			TextureAttribute.createDiffuse(genearateTexture(textureRegions.get("block1")).L)))
        .rect(-0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr,
        		0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr,
        		0.5f*sizemultiplr, 0.5f*sizemultiplr, 0f, 0f, 1f*sizemultiplr);
        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
    			TextureAttribute.createDiffuse(genearateTexture(textureRegions.get("block1")).D)))
        .rect(-0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr,
        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr,
        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, 0f, -1f*sizemultiplr, 0f);
        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
    			TextureAttribute.createDiffuse(genearateTexture(textureRegions.get("block1")).U)))
        .rect(-0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr,
        		0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr,
        		0.5f*sizemultiplr, -0.5f*sizemultiplr, 0f, 1f*sizemultiplr, 0f);
        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
    			TextureAttribute.createDiffuse(genearateTexture(textureRegions.get("block1")).B)))
        .rect(-0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr,
        		0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr,
        		-0.5f*sizemultiplr, -0.5f*sizemultiplr, -1f*sizemultiplr, 0f, 0f);
        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
    			TextureAttribute.createDiffuse(genearateTexture(textureRegions.get("block1")).F)))
        .rect(0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr,
        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr,
        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, 1f, 0f, 0f);
        model = modelBuilder.end();
        //model = modelBuilder.createBox(5f, 5f, 5f, 
        //        new Material(TextureAttribute.createDiffuse(genearateTexture(textureRegions.get("block1")).T)),
        //        Attr);
        instance = new ModelInstance(model);
        
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
		System.out.println("dx: "+direction.x+" dy: "+direction.y+" x: "+position.x+" y: "+position.y+" z: "+position.z);
		Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        modelBatch.begin(cam);
        modelBatch.render(instance,environment);
        modelBatch.end();
	}
	
	@Override
	public void dispose () {
        model.dispose();
	}
}
