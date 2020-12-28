package ru.newlife.gdxproject.world;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import ru.newlife.gdxproject.declaration.BlockDescription;



public class Block {
	
	int texture_size = 16;
	int sizemultiplr = 10;
	
	public TextureRegion texturesplited;
	public BlockTexture texture;
	public ModelInstance instance;
	
	public class BlockTexture {
		public TextureRegion F;
    	public TextureRegion L;
    	public TextureRegion R;
    	public TextureRegion B;
    	public TextureRegion U;
    	public TextureRegion D;
    	
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
	
	public void generateModel() {
		 ModelBuilder modelBuilder = new ModelBuilder();
		 long Attr = VertexAttributes.Usage.Position |
	                VertexAttributes.Usage.TextureCoordinates |
	                VertexAttributes.Usage.Normal;
		 Attribute MAttr = new BlendingAttribute(GL20.GL_SRC_ALPHA|GL20.GL_ONE_MINUS_SRC_ALPHA);
		 	this.texture = this.genearateTexture(texturesplited);
	        modelBuilder.begin();
	        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
	    			TextureAttribute.createDiffuse(this.texture.R), MAttr))
	        .rect(-0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, 
	        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, 
	        		-0.5f*sizemultiplr, -0.5f*sizemultiplr, 0f, 0f, -1f*sizemultiplr);
	        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
	    			TextureAttribute.createDiffuse(this.texture.L), MAttr))
	        .rect(-0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr,
	        		0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr,
	        		0.5f*sizemultiplr, 0.5f*sizemultiplr, 0f, 0f, 1f*sizemultiplr);
	        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
	    			TextureAttribute.createDiffuse(this.texture.D), MAttr))
	        .rect(-0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr,
	        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr,
	        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, 0f, -1f*sizemultiplr, 0f);
	        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
	    			TextureAttribute.createDiffuse(this.texture.U), MAttr))
	        .rect(-0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr,
	        		0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr,
	        		0.5f*sizemultiplr, -0.5f*sizemultiplr, 0f, 1f*sizemultiplr, 0f);
	        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
	    			TextureAttribute.createDiffuse(this.texture.B), MAttr))
	        .rect(-0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr,
	        		0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr,
	        		-0.5f*sizemultiplr, -0.5f*sizemultiplr, -1f*sizemultiplr, 0f, 0f);
	        modelBuilder.part("box", GL20.GL_TRIANGLES, Attr, new Material(
	    			TextureAttribute.createDiffuse(this.texture.F), MAttr))
	        .rect(0.5f*sizemultiplr, -0.5f*sizemultiplr, -0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr,
	        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr, 0.5f*sizemultiplr,
	        		-0.5f*sizemultiplr, 0.5f*sizemultiplr, 1f, 0f, 0f);
	        Model model = modelBuilder.end();
	        this.instance = new ModelInstance(model);
	        
	}
	
	public BlockTexture genearateTexture(TextureRegion sixsideblock) {
    	Texture atlas = sixsideblock.getTexture();
        int x = sixsideblock.getRegionX();
        int y = sixsideblock.getRegionY();
        
        TextureRegion Fside = new TextureRegion(atlas, x+texture_size*0, y, texture_size, texture_size);
        TextureRegion Lside = new TextureRegion(atlas, x+texture_size*1, y, texture_size, texture_size);
        TextureRegion Rside = new TextureRegion(atlas, x+texture_size*2, y, texture_size, texture_size);
        TextureRegion Bside = new TextureRegion(atlas, x+texture_size*3, y, texture_size, texture_size);
        TextureRegion Uside = new TextureRegion(atlas, x+texture_size*4, y, texture_size, texture_size);
        TextureRegion Dside = new TextureRegion(atlas, x+texture_size*5, y, texture_size, texture_size);
        
        BlockTexture texture = new BlockTexture(Fside, Lside, Rside,Bside, Uside,Dside);
     return texture;
        
    }
	
	public Block(String atlasname, int x, int y) {
        Texture atlas = new Texture(atlasname);
        TextureRegion textureslpited[][] = TextureRegion.split( atlas,  atlas.getWidth() / 5,  atlas.getHeight() / 40);
        this.texturesplited = textureslpited[x][y];
        this.generateModel();
	}
	public Block(BlockDescription description) {
		String atlasname = description.path;
		int x = description.x;
		int y = description.y;

        Texture atlas = new Texture(atlasname);
        TextureRegion textureslpited[][] = TextureRegion.split( atlas,  atlas.getWidth() / 5,  atlas.getHeight() / 40);
        this.texturesplited = textureslpited[x][y];
        this.generateModel();
	}
	
	public void setPosition(float x, float y, float z) {
		this.instance.transform.translate(x*sizemultiplr,y*sizemultiplr,z*sizemultiplr);
	}
	
}
