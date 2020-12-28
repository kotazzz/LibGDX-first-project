package ru.newlife.gdxproject.declaration;

import java.util.HashMap;
import java.util.Map;

public class BlockList {
	public static Map<String, BlockDescription> blocks = new HashMap<String, BlockDescription>(){
		private static final long serialVersionUID = 1L;
	{
		put("grass", new BlockDescription("textures\\block\\atlas1.png",0,0));
		put("stone", new BlockDescription("textures\\block\\atlas1.png",0,1));
		put("bedrock", new BlockDescription("textures\\block\\atlas1.png",0,2));
	}};
	
}
