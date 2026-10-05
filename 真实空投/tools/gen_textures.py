# -*- coding: utf-8 -*-
"""Generate ORIGINAL textures for the ported airdrop mod (pixel-art, procedural)."""
import os, math, random
from PIL import Image, ImageDraw

TEX = r'C:\Users\79662\.zcode\workspace\default\dyairdrop-neoforge-1.21.1\src\main\resources\assets\dyairdrop\textures'
random.seed(20261005)

def noise_img(size, base, variance, seed):
    rng = random.Random(seed)
    img = Image.new('RGBA', size)
    px = img.load()
    for y in range(size[1]):
        for x in range(size[0]):
            v = rng.randint(-variance, variance)
            r, g, b = max(0, min(255, base[0]+v)), max(0, min(255, base[1]+v)), max(0, min(255, base[2]+v))
            px[x, y] = (r, g, b, 255)
    return img

def panel(size, base, variance, seed, border_dark, border_light, stripe=None, stripe_color=None):
    """Metal panel with beveled border, optional diagonal hazard stripes."""
    img = noise_img(size, base, variance, seed)
    d = ImageDraw.Draw(img)
    w, h = size
    d.rectangle([0, 0, w-1, h-1], outline=border_dark)
    d.rectangle([1, 1, w-2, h-2], outline=border_light)
    d.rectangle([2, 2, w-3, h-3], outline=border_dark)
    if stripe:
        step = stripe
        for yy in range(-h, w+h, step*2):
            for i in range(step):
                x0, y0 = yy+i, i
                for k in range(h):
                    x = x0 + k - y0
                    if 3 <= x < w-3 and 3 <= k < h-3:
                        d.point((x, k), fill=stripe_color)
    return img

def rivets(img, seed, color=(45,45,48,255), count=8):
    rng = random.Random(seed)
    d = ImageDraw.Draw(img)
    w, h = img.size
    for _ in range(count):
        x = rng.randint(5, w-6); y = rng.randint(5, h-6)
        d.point((x, y), fill=color)
        d.point((x+1, y), fill=(200,200,205,255))
    return img

def ensure(p):
    os.makedirs(p, exist_ok=True)

# ---------- crate skins 64x64 (geo block/entity models reference these) ----------
def crate(size, base, variance, seed, stripe_color, stripe_w=8):
    img = panel(size, base, variance, seed, (25,25,28,255), (170,175,180,255), stripe_w, stripe_color)
    img = rivets(img, seed)
    return img

ensure(os.path.join(TEX, 'block'))
ensure(os.path.join(TEX, 'entities'))
ensure(os.path.join(TEX, 'item'))
ensure(os.path.join(TEX, 'particle'))
ensure(os.path.join(TEX, 'screens'))
ensure(os.path.join(TEX, 'screens', 'atlas'))

# large airdrop: olive drab + amber hazard stripes
crate((64,64), (86,92,62), 10, 11, (196,140,40,255)).save(os.path.join(TEX,'block','largeairdrop.png'))
# glowmask: black with amber stripes only (emissive layer)
g = Image.new('RGBA', (64,64), (0,0,0,0))
d = ImageDraw.Draw(g)
for yy in range(-64, 128, 16):
    for i in range(8):
        for k in range(64):
            x = yy+i+k
            if 0 <= x < 64 and 0 <= k < 64:
                d.point((x,k), fill=(255,170,40,255))
g.save(os.path.join(TEX,'block','largeairdrop_glowmask.png'))
# medical: white/teal crate
crate((64,64), (198,206,208), 8, 12, (52,144,150,255)).save(os.path.join(TEX,'block','airdropmedical.png') if False else os.path.join(TEX,'entities','airdropmedical.png'))
crate((64,64), (198,206,208), 8, 12, (52,144,150,255)).save(os.path.join(TEX,'block','medicalairdrop.png'))
# medical glow: teal cross
g = Image.new('RGBA', (64,64), (0,0,0,0))
d = ImageDraw.Draw(g)
d.rectangle([24,8,40,56], fill=(70,220,230,255))
d.rectangle([8,24,56,40], fill=(70,220,230,255))
g.save(os.path.join(TEX,'entities','airdropmedical_glow.png'))
# weapon: gunmetal + red-orange stripes
crate((64,64), (58,60,66), 9, 13, (200,84,40,255)).save(os.path.join(TEX,'block','weaponairdrop.png'))
crate((64,64), (58,60,66), 9, 13, (200,84,40,255)).save(os.path.join(TEX,'entities','airdropweapon.png'))
# safe: steel blue-gray
crate((64,64), (110,118,126), 8, 14, None).save(os.path.join(TEX,'block','safe.png'))
# safe_2: bronze
crate((64,64), (128,98,60), 10, 15, None).save(os.path.join(TEX,'block','safe_2.png'))
# safeopen: darker steel
crate((64,64), (84,90,96), 8, 16, None).save(os.path.join(TEX,'block','safeopen.png'))
# locked variants: dark charcoal + yellow stripes
crate((64,64), (48,50,54), 8, 17, (222,196,40,255)).save(os.path.join(TEX,'block','lockedlarge.png'))
crate((64,64), (48,50,54), 8, 17, (222,196,40,255)).save(os.path.join(TEX,'block','lockedairdroplarge.png'))
crate((64,64), (44,46,50), 8, 18, (222,196,40,255)).save(os.path.join(TEX,'block','lockedmedical.png'))
crate((64,64), (44,46,50), 8, 18, (222,196,40,255)).save(os.path.join(TEX,'block','lockedairdropmedical.png'))
crate((64,64), (40,42,46), 8, 19, (222,196,40,255)).save(os.path.join(TEX,'block','lockedweapon.png'))
crate((64,64), (40,42,46), 8, 19, (222,196,40,255)).save(os.path.join(TEX,'block','lockedairdropweapon.png'))
# open locked variants: lighter charcoal, no stripes
crate((64,64), (70,72,78), 8, 20, None).save(os.path.join(TEX,'block','lockedlargeopen.png'))
crate((64,64), (70,72,78), 8, 20, None).save(os.path.join(TEX,'block','lockedairdroplargeopen.png'))
crate((64,64), (66,68,74), 8, 21, None).save(os.path.join(TEX,'block','lockedmedicalopen.png'))
crate((64,64), (66,68,74), 8, 21, None).save(os.path.join(TEX,'block','lockedairdropmedicalopen.png'))
crate((64,64), (62,64,70), 8, 22, None).save(os.path.join(TEX,'block','lockedweaponopen.png'))
crate((64,64), (62,64,70), 8, 22, None).save(os.path.join(TEX,'block','lockedairdropweaponopen.png'))
# gre / gre_airdrop / gre_glowmask / mgreen / millitaryloot (extra skins)
crate((64,64), (94,116,78), 10, 23, (40,60,34,255)).save(os.path.join(TEX,'block','gre.png'))
crate((64,64), (94,116,78), 10, 24, (196,140,40,255)).save(os.path.join(TEX,'block','gre_airdrop.png'))
crate((64,64), (94,116,78), 10, 25, (40,60,34,255)).save(os.path.join(TEX,'block','grechest.png'))
crate((64,64), (94,116,78), 10, 26, (196,140,40,255)).save(os.path.join(TEX,'block','grechest2.png'))
crate((64,64), (78,96,66), 9, 27, None).save(os.path.join(TEX,'block','mgreen.png'))
crate((64,64), (72,80,58), 9, 28, (222,196,40,255)).save(os.path.join(TEX,'block','millitaryloot.png'))
g = Image.new('RGBA', (64,64), (0,0,0,0)); d = ImageDraw.Draw(g)
d.rectangle([0,28,64,36], fill=(120,220,120,255))
g.save(os.path.join(TEX,'block','gre_glowmask.png'))
# flare.png block
crate((64,64), (150,72,40), 12, 29, None).save(os.path.join(TEX,'block','flare.png'))

# ---------- plane skins ----------
def plane_skin(base, variance, seed, tail):
    img = noise_img((128,128), base, variance, seed)
    d = ImageDraw.Draw(img)
    d.rectangle([0,0,127,127], outline=(30,32,36,255))
    # windows row
    for x in range(10, 118, 12):
        d.rectangle([x, 58, x+6, 64], fill=(120,190,210,255))
    # tail block
    d.rectangle([96, 8, 124, 40], fill=tail)
    return img
plane_skin((168,172,178), 7, 31, (60,90,150,255)).save(os.path.join(TEX,'entities','plane.png'))
plane_skin((120,126,134), 7, 32, (40,60,110,255)).save(os.path.join(TEX,'entities','planenew.png'))
# glow layer: lit windows only
g = Image.new('RGBA', (128,128), (0,0,0,0)); d = ImageDraw.Draw(g)
for x in range(10, 118, 12):
    d.rectangle([x, 58, x+6, 64], fill=(255,220,140,255))
g.save(os.path.join(TEX,'entities','plane_e.png'))
# gairdrop2/3 (entity par skins)
crate((64,64), (96,102,84), 9, 33, (196,140,40,255)).save(os.path.join(TEX,'entities','gairdrop2.png'))
crate((64,64), (76,82,68), 9, 34, (196,140,40,255)).save(os.path.join(TEX,'entities','gairdrop3.png'))

# ---------- flare gun skins (block + item, 64x64 geo / 16x16 item) ----------
def gun_skin(base, variance, seed, accent):
    img = noise_img((64,64), base, variance, seed)
    d = ImageDraw.Draw(img)
    d.rectangle([0,0,63,63], outline=(20,20,22,255))
    d.rectangle([4,4,59,59], outline=accent)
    d.rectangle([26,26,37,37], fill=accent)
    return img
gun_skin((70,64,58), 10, 41, (214,120,40,255)).save(os.path.join(TEX,'block','flare_gunneo.png'))
gun_skin((58,66,74), 10, 42, (214,120,40,255)).save(os.path.join(TEX,'block','flare_gunneo1.png'))
gun_skin((84,58,52), 10, 43, (230,150,50,255)).save(os.path.join(TEX,'block','flare_gunneo2.png'))
gun_skin((60,72,60), 10, 44, (220,140,50,255)).save(os.path.join(TEX,'block','flare_gunneo3.png'))
gun_skin((72,70,80), 10, 45, (226,132,44,255)).save(os.path.join(TEX,'block','flare_gunneo4.png'))
for i, seed in enumerate([51,52,53,54,55]):
    base = [(70,64,58),(58,66,74),(84,58,52),(60,72,60),(72,70,80)][i]
    gun_skin(base, 10, seed, (214,120,40,255)).save(os.path.join(TEX,'item','flare_gunneo%d.png' % i))

# ---------- particles ----------
# signalsmoke: soft radial puff
img = Image.new('RGBA', (16,16))
px = img.load()
for y in range(16):
    for x in range(16):
        dx, dy = x-7.5, y-7.5
        dist = math.sqrt(dx*dx+dy*dy)
        a = max(0, int(200 * (1 - dist/8.5)))
        px[x,y] = (235,235,240,a)
img.save(os.path.join(TEX,'particle','signalsmoke.png'))
# signalair: bright flare streak
img = Image.new('RGBA', (32,32)); px = img.load()
for y in range(32):
    for x in range(32):
        dx, dy = x-15.5, y-15.5
        dist = math.sqrt(dx*dx+dy*dy)
        a = max(0, int(255 * (1 - dist/15.5)))
        px[x,y] = (255, 160+int(60*(1-dist/16)), 40, a)
img.save(os.path.join(TEX,'particle','signalair.png'))

# ---------- screens (GUI) ----------
def screen_base(size, seed, accent=(64,180,190,255)):
    img = noise_img(size, (34,38,44), 5, seed)
    d = ImageDraw.Draw(img)
    w, h = size
    d.rectangle([0,0,w-1,h-1], outline=(16,18,22,255))
    d.rectangle([1,1,w-2,h-2], outline=(90,96,104,255))
    d.rectangle([2,2,w-3,h-3], outline=(16,18,22,255))
    d.rectangle([4,4,w-5,10], fill=accent)
    return img
screen_base((201,166), 61).save(os.path.join(TEX,'screens','panel.png'))
screen_base((201,166), 62, (150,60,50,255)).save(os.path.join(TEX,'screens','pannel.png'))
screen_base((219,166), 63).save(os.path.join(TEX,'screens','pannel_re.png'))
screen_base((219,166), 64, (150,60,50,255)).save(os.path.join(TEX,'screens','pannel_re_2.png'))
screen_base((201,166), 65).save(os.path.join(TEX,'screens','pannelre.png'))
screen_base((201,166), 66).save(os.path.join(TEX,'screens','test_gui_2.png'))
screen_base((201,166), 67).save(os.path.join(TEX,'screens','testgui.png'))
screen_base((256,256), 68).save(os.path.join(TEX,'screens','airdrop_gui.png'))
screen_base((246,166), 69).save(os.path.join(TEX,'screens','dark.png'))
# small icons
for name, col in [('correct',(90,200,110,255)), ('wrong',(210,80,70,255)), ('green',(90,200,110,255)), ('red',(210,80,70,255)), ('check',(230,200,90,255)), ('check2',(150,150,155,255))]:
    img = Image.new('RGBA', (8,8))
    d = ImageDraw.Draw(img)
    d.rectangle([0,0,7,7], fill=col)
    d.rectangle([2,2,5,5], fill=(240,240,240,255))
    img.save(os.path.join(TEX,'screens',name+'.png'))
for name, col in [('power',(230,190,70,255)), ('power2',(120,110,60,255)), ('button',(70,74,82,255)), ('line3',(52,58,66,255)), ('test3',(44,50,58,255))]:
    size = (167,22) if name=='test3' else ((60,20) if name=='button' else ((167,4) if name=='line3' else (16,16)))
    img = Image.new('RGBA', size); d = ImageDraw.Draw(img)
    d.rectangle([0,0,size[0]-1,size[1]-1], fill=col)
    img.save(os.path.join(TEX,'screens',name+'.png'))
# key icons a1..f1 (up) a2..f2 (down)
keys = ['a','b','c','d','e','f']
for idx, k in enumerate(keys):
    col = [(90,110,130,255),(110,120,90,255),(130,100,90,255),(90,110,130,255),(120,100,130,255),(100,120,110,255)][idx]
    for suffix, shade in [('1', 1.0), ('2', 0.65)]:
        img = Image.new('RGBA', (20,20)); d = ImageDraw.Draw(img)
        c = tuple(int(v*shade) for v in col)
        d.rectangle([0,0,19,19], fill=c, outline=(20,20,24,255))
        d.rectangle([4,4,15,15], outline=(220,225,230,255))
        img.save(os.path.join(TEX,'screens','%s%s.png' % (k, suffix)))
# atlas image buttons (39x22 frame, 2 frames stacked = 39x44)
for name in ['a1','b1','c1','d1','e1','f1','check','power']:
    img = Image.new('RGBA', (39,44))
    d = ImageDraw.Draw(img)
    up = (70,84,96,255) if name not in ('check','power') else (120,110,60,255)
    down = tuple(int(v*0.6) for v in up)
    d.rectangle([0,0,38,21], fill=up, outline=(18,20,24,255))
    d.rectangle([0,22,38,43], fill=down, outline=(18,20,24,255))
    d.rectangle([8,9,30,13], fill=(225,228,232,255))
    d.rectangle([8,31,30,35], fill=(225,228,232,255))
    img.save(os.path.join(TEX,'screens','atlas','imagebutton_%s.png' % name))
# flare entity texture + entity crate textures used by synced TEXTURE names
crate((64,64), (150,72,40), 12, 30, None).save(os.path.join(TEX,'entities','flare.png'))
print('textures generated')
