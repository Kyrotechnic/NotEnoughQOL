package me.oringo.oringoclient.mixins.transformers;

import me.oringo.oringoclient.OringoClient;
import net.minecraft.launchwrapper.IClassTransformer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.stream.Collectors;

public class FastLoadingTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        return basicClass;
    }
}
