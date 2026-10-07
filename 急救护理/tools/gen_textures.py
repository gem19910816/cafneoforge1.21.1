# -*- coding: utf-8 -*-
"""Regenerates the original SelfAid item textures (16x16 PNG, original pixel art)."""
from PIL import Image, ImageDraw
import os

OUT = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
                   'src', 'main', 'resources', 'assets', 'selfaid', 'textures', 'item')
os.makedirs(OUT, exist_ok=True)


def base():
    return Image.new('RGBA', (16, 16), (0, 0, 0, 0))


# ---------- bandage: white rolled bandage ----------
img = base(); d = ImageDraw.Draw(img)
d.ellipse([2, 4, 12, 13], fill=(235, 235, 230, 255), outline=(150, 150, 145, 255))
d.ellipse([5, 6, 9, 10], fill=(200, 200, 195, 255))
d.arc([2, 4, 12, 13], start=-60, end=80, fill=(170, 170, 165, 255))
d.polygon([(12, 6), (15, 4), (15, 8), (12, 9)], fill=(225, 225, 220, 255), outline=(150, 150, 145, 255))
img.save(os.path.join(OUT, 'bandage.png'))

# ---------- plaster: beige adhesive bandage, diagonal ----------
img = base(); d = ImageDraw.Draw(img)
pad_color = (222, 196, 150, 255); pad_edge = (180, 150, 100, 255); pad_center = (240, 225, 190, 255)
d.polygon([(3, 11), (11, 3), (13, 5), (5, 13)], fill=pad_color, outline=pad_edge)
d.polygon([(6, 10), (10, 6), (11, 7), (7, 11)], fill=pad_center)
for px, py in [(4, 10), (10, 4), (5, 12), (12, 6)]:
    d.point((px, py), fill=(200, 170, 120, 255))
img.save(os.path.join(OUT, 'plaster.png'))

# ---------- morphine: syringe ----------
img = base(); d = ImageDraw.Draw(img)
metal = (190, 195, 200, 255); metal_d = (130, 135, 140, 255); glass = (210, 225, 235, 255); liquid = (90, 150, 220, 255)
d.rectangle([4, 6, 11, 12], fill=glass, outline=metal_d)
d.rectangle([5, 9, 10, 11], fill=liquid)
d.rectangle([2, 8, 4, 10], fill=metal)
d.rectangle([0, 7, 2, 11], fill=metal_d)
d.line([(11, 9), (15, 5)], fill=metal_d)
d.point((15, 5), fill=(255, 255, 255, 255))
img.save(os.path.join(OUT, 'morphine.png'))

# ---------- first aid kit: white case with red cross ----------
img = base(); d = ImageDraw.Draw(img)
red = (200, 40, 45, 255); white = (240, 240, 240, 255); edge = (120, 120, 120, 255)
d.rectangle([1, 4, 14, 14], fill=white, outline=edge)
d.rectangle([1, 4, 14, 6], fill=red)
d.rectangle([5, 2, 10, 4], outline=edge, fill=(200, 200, 200, 255))
d.rectangle([7, 7, 8, 12], fill=red)
d.rectangle([5, 9, 10, 10], fill=red)
d.rectangle([7, 13, 8, 14], fill=(255, 210, 60, 255))
img.save(os.path.join(OUT, 'first_aid_kit.png'))
print('textures regenerated to', OUT)
