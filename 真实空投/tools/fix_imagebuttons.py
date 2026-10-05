# -*- coding: utf-8 -*-
import os, re

GUI_DIR = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\java\net\mcreator\dyairdrop\client\gui'

pat = re.compile(
    r'this\.(\w+) = new ImageButton\(\s*'
    r'this\.leftPos \+ (-?[\d.]+),\s*this\.topPos \+ (-?[\d.]+),\s*(-?\d+),\s*(-?\d+),\s*(-?\d+),\s*(-?\d+),\s*(-?\d+),\s*'
    r'new ResourceLocation\("([^"]+)"\),\s*(-?\d+),\s*(-?\d+),\s*e -> \{\s*'
    r'PacketDistributor\.sendToServer\(new (\w+)\((\d+),\s*this\.x,\s*this\.y,\s*this\.z\)\);\s*'
    r'\w+\.handleButtonAction\(this\.entity,\s*(\d+),\s*this\.x,\s*this\.y,\s*this\.z\);\s*'
    r'\}\s*\);')

def repl(m):
    (field, x, y, w, h, u, v, hoverv, tex, tw, th, payload, btnid, btnid2) = m.groups()
    return (
        'this.%s = Button.builder(Component.empty(), e -> {\n'
        '            PacketDistributor.sendToServer(new %s(%s, this.x, this.y, this.z));\n'
        '            %s.handleButtonAction(this.entity, %s, this.x, this.y, this.z);\n'
        '         }).bounds(this.leftPos + %s, this.topPos + %s, %s, %s).build(builder -> new Button(builder) {\n'
        '            @Override\n'
        '            public void renderWidget(GuiGraphics guiGraphics, int gx, int gy, float ticks) {\n'
        '               guiGraphics.blit(new ResourceLocation("%s"), this.getX(), this.getY(), 0.0F, this.isHoveredOrFocused() ? %s.0F : 0.0F, %s, %s, %s, %s);\n'
        '            }\n'
        '         });') % (field, payload, btnid, payload, btnid2, x, y, w, h, tex, hoverv, w, h, tw, th)

count = 0
for fn in os.listdir(GUI_DIR):
    if not fn.endswith('.java'):
        continue
    p = os.path.join(GUI_DIR, fn)
    t = open(p, encoding='utf-8').read()
    if 'new ImageButton' not in t:
        continue
    new = pat.sub(repl, t)
    # field type declarations: ImageButton imagebutton_x -> Button imagebutton_x
    new = re.sub(r'\bImageButton (\w+);', r'Button \1;', new)
    new = new.replace('import net.minecraft.client.gui.components.ImageButton;\n', '')
    open(p, 'w', encoding='utf-8').write(new)
    count += 1
    left = 'new ImageButton' in new
    print(fn, 'converted' if not left else 'STILL HAS IMAGEBUTTON')
print('files:', count)
